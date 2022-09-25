import {Injectable} from "@angular/core";
import {BehaviorSubject, Subject} from "rxjs";
import {User} from "../models/User";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn: Subject<boolean> = new Subject<boolean>();
  jwtToken: string = '';
  currentUser: User = new User();

  constructor() {

  }
}
