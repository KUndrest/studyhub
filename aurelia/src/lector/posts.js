import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';

export class post {

  subjectList = [];
  postData = {}
  postList = [];

  constructor() {
  }
  getSubjects() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
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

    client.fetch('http://localhost:8080/posts')
      .then(response => response.json())
      .then(posts => this.postList = posts);
  }
}
