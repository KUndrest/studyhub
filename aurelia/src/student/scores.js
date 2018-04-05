import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';

export class score {
  subjectList = [];
  scoreData = {};

  constructor() {
  }

  getSubjects() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'subjects')
      .then(response => response.json())
      .then(subjects => this.subjectList = subjects);
  }

  /*
  getScoreData(personId, headerId) {
    console.log(personId, headerId);
    let header = this.headerList.find(h => h.id == headerId);
    let score = header.scores.find(s => s.subjectPerson.id == personId);
    if (score) {
      return score.score;
    }

    return '';
  }
  getHeaders() {
    let client = new HttpClient();

    return client.fetch(environment.apiUrl + 'headers/' + this.studyHubService.selectedSubject.id)
      .then(response => response.json())
      .then(header => this.headerList = header);
  }
  */
}
