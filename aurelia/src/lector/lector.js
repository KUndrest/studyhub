import {HttpClient, json} from 'aurelia-fetch-client';
import 'bootstrap';

export class lector {

  subjectData = {}
  subjectList = [];

  constructor() {
  }
  activate() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  addSubject() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/subjects/add', {
      'method': 'POST',
      'body': json(this.subjectData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Server saatis' + data.subject);
      });

    console.log('TEHTUD');
  }
}

