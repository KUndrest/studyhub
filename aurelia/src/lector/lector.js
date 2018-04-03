import {HttpClient, json} from 'aurelia-fetch-client';
import 'bootstrap';
import environment from '../environment';

export class lector {
  subjectData = {};
  subjectList = [];


  constructor() {
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
        console.log('Server saatis' + data.post);
      });
  }
}

