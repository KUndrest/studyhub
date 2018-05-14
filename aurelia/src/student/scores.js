import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';

export class score {
  subjectList = [];
  scoreList=[];
  searchString = '';

  constructor() {
    this.getScores();
    this.getSubjects();
  }

  getScores() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'scores')
      .then(response => response.json())
      .then(scores => this.scoreList = scores);
  }

  getSubjects() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
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
