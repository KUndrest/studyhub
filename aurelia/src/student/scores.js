import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';

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
  /*
  activate() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'scores')
      .then(response => response.json())
      .then(scores => this.scoreList = scores);
  }
  getHeaders() {
    let client = new HttpClient();

    return client.fetch(environment.apiUrl + 'headers')
      .then(response => response.json())
      .then(header => this.headerList = header);
  }
  */
}
