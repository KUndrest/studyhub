import 'bootstrap';
import {HttpClient, json} from 'aurelia-fetch-client';

export class home {
  constructor() {
  }

  personData = {};

  addPerson() {
    let client = new HttpClient();
    if ($('#passwordRegister').val() === $('#confirm-password').val()) {
      client.fetch('http://localhost:8080/person/add', {
        'method': 'POST',
        'body': json(this.personData)
      })
        .then(response => response.json())
        .then(data => {
          console.log('Server saatis ' + data.name);
        });

      if (personData.studentCode !== null) {
        window.location.replace('http://localhost:9000/#/student');
      } else {
        window.location.replace('http://localhost:9000/#/lector');
      }
    } else {
      $('input#passwordRegister').addClass('is-invalid');
      $('input#confirm-password').addClass('is-invalid');
      $('.registrationPasswordMatch').html("Passwords don't match");
    }
  }
  studentCheck() {
    if (!document.getElementById('student').checked) {
      document.getElementById('studentCode').style.display = 'none';
      return true;
    } else {
      document.getElementById('studentCode').style.display = 'block';
      return false;
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
}
