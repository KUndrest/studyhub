export class App {
  constructor() {
    this.message = 'Tere!';
  }
  configureRouter(config, router) {
    this.router = router;
    config.title = 'StudyHub';
    config.map([
      { route: ['', 'home'],       name: 'home',       moduleId: 'home/index' },
      { route: 'users',            name: 'users',      moduleId: 'users/index', nav: true, title: 'Users' },
      //{ route: 'users/:id/detail', name: 'userDetail', moduleId: 'users/detail' },
      //{ route: 'files/*path',      name: 'files',      moduleId: 'files/index', nav: 0,    title: 'Files', href:'#files' }
    ]);
  }
}
