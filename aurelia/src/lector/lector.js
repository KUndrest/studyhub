import {HttpClient, json} from 'aurelia-fetch-client';
import 'bootstrap';
import environment from '../environment';
import {inject} from 'aurelia-framework';
import {StudyHubService} from '../studyhub-service/studyhub-service';

@inject(StudyHubService)
export class lector {
  subjectData = {};
  subjectList = [];
  selectedSubject;


  constructor(studyHubService) {
    this.studyHubService = studyHubService;
  }

  activate() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  addSubject() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects/add', {
      'method': 'POST',
      'body': json(this.subjectData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Server saatis' + data.subject);
        $('#newSubject').modal('hide');
        this.activate();
      });
  }

  newSubject() {
    this.subjectData = {};
    $('#newSubject').modal();
  }

  subjectSelected(subject) {
    this.studyHubService.selectedSubject = subject;
    window.location.href = "http://localhost:9000/#/lectorposts";
  }

  editSubject(subjectData) {
    this.subjectData = subjectData;
    $('#editSubject').modal();
  }

  removeSubject(id) {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects/' + id, {
      'method': 'DELETE'
    }).then(() => {
      console.log('subject deleted');
      this.activate();
    });
  }
  saveSubjectEdit() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subject', {
      'method': 'POST',
      'body': json(this.subjectData)
    })
      .then(response => response.json())
      .then(() => {
        $('#editSubject').modal('hide');
        this.subjectData = {};
      });
  }
}

