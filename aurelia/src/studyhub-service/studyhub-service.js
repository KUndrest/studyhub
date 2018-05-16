export class StudyHubService {
  _selectedSubject;
  _personData;

  get activePersonId() {
    return this.personData.id;
  }

  set personData(personData) {
    window.localStorage.setItem('personData', JSON.stringify(personData));
    this._personData = personData;
  }

  get personData() {
    if (!this._personData) {
      this._personData = JSON.parse(window.localStorage.getItem('personData') || '{}');
    }

    return this._personData;
  }

  get selectedSubject() {
    if (!this._selectedSubject) {
      return JSON.parse(localStorage.getItem('selectedSubject'));
    }

    return this._selectedSubject;
  }

  set selectedSubject(subject) {
    window.localStorage.setItem('selectedSubject', JSON.stringify(subject));
    this._selectedSubject = subject;
    window.location.reload();
  }
}
