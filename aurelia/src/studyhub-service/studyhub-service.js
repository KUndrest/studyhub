export class StudyHubService {
  _selectedSubject;
  activePersonId = 38; // Tulevikus peaks olema siin sisselogitud kasutaja id

  get selectedSubject() {
    if (!this._selectedSubject) {
      return JSON.parse(localStorage.getItem("selectedSubject"));
    }

    return this._selectedSubject;
  }

  set selectedSubject(subject) {
    window.localStorage.setItem("selectedSubject", JSON.stringify(subject));
    this._selectedSubject = subject;
    window.location.reload();
  }
}
