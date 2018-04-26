import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';
import {inject} from 'aurelia-framework';
import environment from '../environment';
import {StudyHubService} from '../studyhub-service/studyhub-service';

@inject(StudyHubService)
export class post {

  subjectList = [];
  postData = {};
  subjectData = {};
  postList = [];
  selectedSubject;

  constructor(studyHubService) {
    this.studyHubService = studyHubService;
  }

  subjectSelected(subject) {
    this.studyHubService.selectedSubject = subject;
  }

  getSubjects() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  activate() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'posts/' + this.studyHubService.selectedSubject.id)
      .then(response => response.json())
      .then(posts => this.postList = posts);
  }

  addPost() {
    let client = new HttpClient();
    this.postData.subject = this.studyHubService.selectedSubject;

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

  addSubject() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects/add', {
      'method': 'POST',
      'body': json(this.subjectData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Server saatis' + data.subject);
        $('#newSubject').modal('hide');
        this.getSubjects();
      });
  }

  newSubject() {
    this.subjectData = {};
    $('#newSubject').modal();
  }

  savePostEdit() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'post', {
      'method': 'POST',
      'body': json(this.postData)
    })
      .then(response => response.json())
      .then(() => {
        $('#editPost').modal('hide');
        this.postData = {};
      });
  }

  viewPost(postData) {
    this.postData = postData;
    $('#viewPost').modal();
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
}
