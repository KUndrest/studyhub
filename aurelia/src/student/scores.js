import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';
import {StudyHubService} from "../studyhub-service/studyhub-service";
import {inject} from "aurelia-framework";

@inject(StudyHubService)
export class score {
  subjectList = [];
  scoreList=[];
  searchString = '';

  constructor(studyHubService) {
    this.studyHubService = studyHubService;
    this.getScores();
    this.getSubjects();
  }

  getScores() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'latest-scores/' + this.studyHubService.activePersonId)
      .then(response => response.json())
      .then(scores => this.scoreList = scores);
  }

  getSubjects() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'person-subjects/' + this.studyHubService.activePersonId)
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  addSubject() {
    let client = new HttpClient();
    client.fetch(environment.apiUrl + 'subjectPersons/add', {
      'method': 'POST',
      'body': json(this.subjectData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Done');
        $('#registerSubject').modal('hide');
        this.getSubjects();
      });
  }

  searchSubject() {
    let client = new HttpClient();
    client.fetch(environment.apiUrl + 'subjects/search/' + this.searchString)
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  registerSubject() {
    document.getElementById('search').value = '';
    $('#registerSubject').modal();
  }
}
