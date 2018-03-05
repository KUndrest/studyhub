import {HttpClient} from 'aurelia-fetch-client';
import 'bootstrap';

export class student {
  subjectList = [];

  constructor() {
  }
  activate() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }
}
