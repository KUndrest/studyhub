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
        this.postData = {};
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

  registerSubject() {
    //loob seose tudengi ja valitud aine vahele, paneb modeli kinni ja kutsub getSubjecti uuesti
  }

  subjectList() {
    this.subjectData = {};
    $('#registerSubject').modal();
  }
}
