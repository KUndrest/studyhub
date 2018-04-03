import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';

export class score {
  subjectList = [];
  scoreList = [];
  scoreData = {
    isMark: false
  };
  subjectData = {};
  people = [];

  scoreChoices = null;

  constructor() {
    this.getPeople();
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
  addHeader() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'scores/add', {
      'method': 'POST',
      'body': json(this.scoreData)
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
      'body': json(this.scoreData)
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
