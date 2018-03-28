import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';
import environment from './environment';

export class score {
  subjectList = [];
  scoreList = [];
  scoreData = {};

  constructor() {
  }
  getSubjects() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
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
  /*activate() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'scores')
      .then(response => response.json())
      .then(scores => this.scoreList = scores);
  }*/
}
