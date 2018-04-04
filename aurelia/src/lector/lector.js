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
        this.activate();
      });
  }
  subjectSelected(subject) {
    this.studyHubService.selectedSubject = subject;
  }
}

