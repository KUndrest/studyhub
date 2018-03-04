import {HttpClient, json} from 'aurelia-fetch-client';
import 'bootstrap';

export class lector {

  subjectData = {}
  subjectList = [];

  constructor() {
    this.subject = '';
    this.code = '';
  }
  activate() {
    let client = new HttpClient();

    client.fetch('http://localhost:9000/subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  addSubject() {
    let client = new HttpClient();

    client.fetch('http://localhost:9000/subjects/add', {
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

