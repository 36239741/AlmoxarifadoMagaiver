import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BehaviorTitleService {
  private behaviorTitle: BehaviorSubject<String> = new BehaviorSubject('');
  constructor() { }

  setBehaviorTitle(title: String){
    this.behaviorTitle.next(title);
  }
  getBehaviorTitle(): Observable<String> {
    return this.behaviorTitle.asObservable();
  }
}
