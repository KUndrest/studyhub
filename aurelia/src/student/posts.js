import 'bootstrap';
import {HttpClient} from 'aurelia-fetch-client';

export class post {
  postList = [];

  constructor() {
  }
  activate() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/posts')
      .then(response => response.json())
      .then(posts => this.postList = posts);
  }
}
