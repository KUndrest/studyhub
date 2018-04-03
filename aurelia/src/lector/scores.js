import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';
import {inject} from 'aurelia-framework';
import {StudyHubService} from "../studyhub-service/studyhub-service";

@inject(StudyHubService)
export class score {
  subjectList = [];
  headerList = [];
  headerData = {
    isMark: false
  };
  subjectData = {};
  people = [];

  constructor(studyHubService) {
    this.studyHubService = studyHubService;
    this.getPeople();
    this.getHeaders();
    console.log(this.studyHubService.selectedSubject);
  }

  getPeople(){
    let client = new HttpClient();

    return client.fetch(environment.apiUrl + 'person')
      .then(response => response.json())
      .then(person => this.people = person);
  }

  getSubjects() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  getHeaders() {
    let client = new HttpClient();

    return client.fetch(environment.apiUrl + 'headers')
      .then(response => response.json())
      .then(header => this.headerList = header);
  }

  addHeader() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'headers/add', {
      'method': 'POST',
      'body': json(this.headerData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Server saatis' + data.score);
      });
  }
  /*addScore() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'scores/add', {
      'method': 'POST',
      'body': json(this.headerData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Server saatis' + data.score);
      });
  }*/
  addSubject() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects/add', {
      'method': 'POST',
      'body': json(this.subjectData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Server saatis' + data.post);
      });
  }
  /*activate() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'scores')
      .then(response => response.json())
      .then(scores => this.scoreList = scores);
  }*/
}
