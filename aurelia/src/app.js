import 'bootstrap';

export class App {
  configureRouter(config, router) {
    this.router = router;
    config.title = 'StudyHub';
    config.map([
      { route: ['', 'home'],       name: 'home',       moduleId: 'home/index', nav: true },
      { route: 'lector',            name: 'lector',      moduleId: 'lector/lector', nav: true, title: 'Õppejõud' },
      { route: 'users/:id/detail', name: 'userDetail', moduleId: 'users/detail' },
      { route: 'files/*path',      name: 'files',      moduleId: 'files/index', nav: 0,    title: 'Files', href: '#files' }
    ]);
  }
}
