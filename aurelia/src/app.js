export class App {
  configureRouter(config, router) {
    this.router = router;
    config.title = 'StudyHub';
    config.map([
      { route: ['', 'home'],       name: 'home',       moduleId: 'home/home', nav: true },
      { route: 'lector',            name: 'lector',      moduleId: 'lector/lector', nav: true, title: 'Lector' },
      { route: 'lectorposts',            name: 'lectorposts',      moduleId: 'lector/posts', nav: true, title: 'Posts' },
      { route: 'lectorscores',            name: 'lectorscores',      moduleId: 'lector/scores', nav: true, title: 'Scores' },
      { route: 'student',            name: 'student',      moduleId: 'student/student', nav: true, title: 'Student' },
      { route: 'studentposts',            name: 'studentposts',      moduleId: 'student/posts', nav: true, title: 'Posts' },
      { route: 'studentscores',            name: 'studentscores',      moduleId: 'student/scores', nav: true, title: 'Scores' },
      { route: 'users/:id/detail', name: 'userDetail', moduleId: 'users/detail' },
      { route: 'files/*path',      name: 'files',      moduleId: 'files/index', nav: 0,    title: 'Files', href: '#files' }
    ]);
  }
}


