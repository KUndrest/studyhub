import {HttpClient, json} from 'aurelia-fetch-client';

export class lector {

  subjectData = {}

  constructor() {
    this.subject = '';
    this.code = '';
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

