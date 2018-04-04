import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';

export class post {
  subjectList = [];
  postData = {}
  postList = [];

  constructor() {
  }
  getSubjects() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  addPost() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'posts/add', {
      'method': 'POST',
      'body': json(this.postData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Server saatis' + data.title);
        this.activate();
      });
  }

  activate() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'posts')
      .then(response => response.json())
      .then(posts => this.postList = posts);
  }
}
