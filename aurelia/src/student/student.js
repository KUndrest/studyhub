import {HttpClient, json} from 'aurelia-fetch-client';
import 'bootstrap';
import environment from '../environment';

export class student {
  postData = {};
  subjectList = [];
  postList = [];
  scoreList = [];

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
  savePostEdit() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'post', {
      'method': 'POST',
      'body': json(this.postData)
    })
      .then(response => response.json())
      .then(data => {
        $('#editPost').modal('hide');
      });
  }

  editPost(postData) {
    this.postData = postData;
    $('#editPost').modal();
  }

  removePost(id) {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'posts/' + id, {
      'method': 'DELETE'
    }).then(() => {
      console.log('post deleted');
      this.activate();
    });
  }
  activate() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'posts')
      .then(response => response.json())
      .then(posts => this.postList = posts);
  }
  /*getScores() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'scores')
      .then(response => response.json())
      .then(scores => this.scoreList = scores);
  }*/
}
