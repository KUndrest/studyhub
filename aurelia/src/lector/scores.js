import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';
import {inject} from 'aurelia-framework';
import {StudyHubService} from '../studyhub-service/studyhub-service';

@inject(StudyHubService)
export class score {
  headerList = [];
  headerData = {
    isMark: false
  };
  subjectList = [];
  subjectData = {};
  people = [];
  selectedSubject;
  scoreData = {};
  selectedRow = '';

  constructor(studyHubService) {
    this.studyHubService = studyHubService;
    this.getPeople();
    this.getHeaders();
    console.log(this.studyHubService.selectedSubject);
  }

  subjectSelected(subject) {
    this.studyHubService.selectedSubject = subject;
  }

  getPeople() {
    let client = new HttpClient();

    if (this.studyHubService.selectedSubject) {
      client.fetch(environment.apiUrl + 'subjectPersons/' + this.studyHubService.selectedSubject.id)
        .then(response => response.json())
        .then (person => this.people = person);
    }
  }

  getSubjects() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  getHeaders() {
    let client = new HttpClient();

    return client.fetch(environment.apiUrl + 'headers/' + this.studyHubService.selectedSubject.id)
      .then(response => response.json())
      .then(header => this.headerList = header);
  }

  addHeader() {
    let client = new HttpClient();
    this.headerData.subject = this.studyHubService.selectedSubject;
    console.log(this.headerData);
    client.fetch(environment.apiUrl + 'headers/add', {
      'method': 'POST',
      'body': json(this.headerData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Server saatis' + data);
        this.getHeaders();
      });
  }

  getScoreData(personId, headerId) {
    console.log(personId, headerId);
    let header = this.headerList.find(h => h.id == headerId);
    let score = header.scores.find(s => s.subjectPerson.id == personId);
    if (score) {
      return score.score;
    }

    return '';
  }

  startEditingScore(subjectPerson, header) {
    this.scoreData.subjectPerson = subjectPerson;
    this.scoreData.header = header;
    this.scoreData.score = this.getScoreData(subjectPerson.id, header.id);
    this.selectedRow = subjectPerson.id + ':' + header.id;
  }

  saveScoreData() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'scores/add', {
      'method': 'POST',
      'body': json(this.scoreData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Server saatis' + data.subject);
        this.selectedRow = '';
        this.getHeaders();
      });
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
        this.getSubjects();
      });
  }
  /*activate() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'scores')
      .then(response => response.json())
      .then(scores => this.scoreList = scores);
  }*/
}
