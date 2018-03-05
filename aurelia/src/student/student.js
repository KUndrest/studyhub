import {HttpClient} from 'aurelia-fetch-client';
import 'bootstrap';

export class student {
  postData = {};
  subjectList = [];

  constructor() {
  }
  addPost() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/posts/add', {
      'method': 'POST',
      'body': json(this.postData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Server saatis' + data.post);
      });
  }
  activate() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }
}
