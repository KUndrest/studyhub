import {HttpClient, json} from 'aurelia-fetch-client';
import 'bootstrap';
import environment from '../environment';

export class student {
  postData = {};
  subjectList = [];
  postList = [];
  scoreList = [];
  headerList= []

  constructor() {
    this.getScores();
    this.getHeaders();
  }

  getScores() {
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

  getSubjects() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  activate() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'posts')
      .then(response => response.json())
      .then(posts => this.postList = posts);
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
        $('#newPost').modal('hide');
        this.activate();
      });
  }

  newPost() {
    this.postData = {};
    $('#newPost').modal();
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

  /*
  getScores() {
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

  addSubject() {
    let client = new HttpClient();
    client.fetch(environment.apiUrl + 'subjectPersons/add', {
      'method': 'POST',
      'body': json(this.subjectData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Done');
        $('#registerSubject').modal('hide');
        this.getSubjects();
      });
  }

  registerSubject() {
    this.subjectData = {};
    $('#registerSubject').modal();
  }
}
