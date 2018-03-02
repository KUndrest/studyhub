import {HttpClient, json} from 'aurelia-fetch-client'

export class lector {

  userData = {}

  constructor(){
    this.subject = "Aine nimetus"
    this.code = "Ainekood"
  }
  addSubject(){
    let client = new HttpClient();

    client.fetch('http://localhost:9000/subjects/add', {
      'method': "POST",
      'body': json(userData)
    })
      .then(response => response.json())
      .then(data => {
        console.log("Server saatis " + data.subject);
      });

    console.log("TEHTUD")
  }
}

