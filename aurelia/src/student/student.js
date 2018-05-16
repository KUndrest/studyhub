import {HttpClient, json} from 'aurelia-fetch-client';
import 'bootstrap';
import environment from '../environment';
import {StudyHubService} from '../studyhub-service/studyhub-service';
import {inject} from 'aurelia-framework';

@inject(StudyHubService)
export class student {
  postData = {};
  subjectList = [];
  postList = [];
  headerList = [];
  searchString = '';

  studyHubService;

  constructor(studyHubService) {
    this.studyHubService = studyHubService;
    this.getScores();
    this.getHeaders();
  }

  getScores() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'latest-scores/' + this.studyHubService.activePersonId)
      .then(response => response.json())
      .then(scores => this.scores = scores);
  }

  getHeaders() {
    let client = new HttpClient();

    return client.fetch(environment.apiUrl + 'headers')
      .then(response => response.json())
      .then(header => this.headerList = header);
  }

  getSubjects() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects/' + this.studyHubService.activePersonId)
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  activate() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'person-posts/'  + this.studyHubService.activePersonId)
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
    this.postData = {
      person: {id: this.studyHubService.activePersonId}
    };
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

  searchSubject() {
    let client = new HttpClient();
    client.fetch(environment.apiUrl + 'subjects/search/' + this.searchString)
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  registerSubject() {
    document.getElementById('search').value = '';
    $('#registerSubject').modal();
  }
}
