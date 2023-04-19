import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class UserStoreService {

  private fullName$ = new BehaviorSubject<string>("");
    private role$ = new BehaviorSubject<string>(""); 

    constructor(){}
 
    public getRoleFromStore(){
        return this.role$.asObservable();
    }
    public setRoleFromStore(role:string){
        return this.role$.next(role);
    }

    public getFullNameFromStore(){
        return this.fullName$.asObservable();
    }
    public setFullNameFromStore(fullname:string){
        return this.role$.next(fullname);
    }
}
