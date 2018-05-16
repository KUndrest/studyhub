import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';
import environment from '../environment';
import {StudyHubService} from '../studyhub-service/studyhub-service';
import {inject} from 'aurelia-framework';

@inject(StudyHubService)
export class home {
  constructor(study) {
    this.study = study;
  }

  personData = {};
  study;

  addPerson() {
    let client = new HttpClient();
    if ($('#passwordRegister').val() === $('#confirm-password').val()) {
      client.fetch(environment.apiUrl + 'person/add', {
        'method': 'POST',
        'body': json(this.personData)
      })
        .then(response => response.json())
        .then(data => {
          this.study.personData = data;
          if (data.studentCode) {
            window.location.replace('http://localhost:9000/#/student');
          } else if(!data.studentCode){
            window.location.replace('http://localhost:9000/#/lector');
          }
        });
    } else {
      $('input#passwordRegister').addClass('is-invalid');
      $('input#confirm-password').addClass('is-invalid');
      $('.registrationPasswordMatch').html("Passwords don't match");
    }
  }

  studentCheck() {
    if (!document.getElementById('student').checked) {
      document.getElementById('studentCode').disabled = true;
      document.getElementById('studentCode').value = '';
      return true;
    } else {
      document.getElementById('studentCode').disabled = false;
      return true;
    }
  }

  changeForm() {
    $('#login-form-link').click(function(e) {
      $('#login-form').delay(100).fadeIn(100);
      $('#register-form').fadeOut(100);
      $('#register-form-link').removeClass('active');
      $(this).addClass('active');
      e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
      $('#register-form').delay(100).fadeIn(100);
      $('#login-form').fadeOut(100);
      $('#login-form-link').removeClass('active');
      $(this).addClass('active');
      e.preventDefault();
    });
  }

  login() {
    let client = new HttpClient();

    client.fetch(environment.apiUrl + 'login', {
      'method': 'POST',
      'body': json(this.personData)
    })
      .then(response => response.json())
      .then(data => {
        this.study.personData = data;
        if (data.studentCode) {
          window.location.replace('http://localhost:9000/#/student');
        } else if(!data.studentCode){
          window.location.replace('http://localhost:9000/#/lector');
        } else{
          $('.loginPasswordMatch').html("User and password don't match");
        }
      });
  }
}
