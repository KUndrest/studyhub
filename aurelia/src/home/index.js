import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';

export class home {
  constructor() {
  }

  personData = {};

  addPerson() {
    let client = new HttpClient();

    client.fetch('http://localhost:8080/person/add', {
      'method': 'POST',
      'body': json(this.personData)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Server saatis ' + data.name);
      });
  }

    /*
    $(function() {
        $('#login-form-link').click(function(e) {
            $("#login-form").delay(100).fadeIn(100);
            $("#register-form").fadeOut(100);
            $('#register-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
        $('#register-form-link').click(function(e) {
            $("#register-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $('#login-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
    }); */
}
