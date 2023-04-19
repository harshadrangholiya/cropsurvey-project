import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User} from './user';

const endpoint = 'http://localhost:8080/user/get';
@Injectable({
  providedIn: 'root'
})
export class UserService {
 
 
  private baseURL = "http://localhost:8080/user";
  private url ="http://localhost:8080/user/signup";
  private url1="http://localhost:8080/user/login";
  private url2="http://localhost:8080/user/get";
  private url3="http://localhost:8080/user/";
  private url4="http://localhost:8080/user/forgetPassword"
  private url5="http://localhost:8080/user/changePassword";
  
  constructor(private httpClient: HttpClient) { }
  getUserList() : Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseURL}`);
  }
  registerUser(user: User): Observable<Object>{
    return this.httpClient.post(`${this.url}`, user);
  }
  loginUser(user: User): Observable<Object>{
    return this.httpClient.post(`${this.url1}`, user);
  }
  forgetPassword(data:any){
    return this.httpClient.post(`${this.url4}`, data);
  }
  changePassword(data:any){
    return this.httpClient.post(this.url5, data);
  }
  
  
getAllUser():Observable<User[]>{
  let header = new HttpHeaders();
  header.append("Authorization","Bearer "+ localStorage.getItem("token"));
return this.httpClient.get<User[]>(`${this.url2}`,{ 'headers': header });
}
deleteUser(id:number):Observable<any>
{
  let header = new HttpHeaders();
  header.append("Authorization","Bearer "+ localStorage.getItem("token"));
 return this.httpClient.delete(this.url3.concat(id+""),{ 'headers': header });
}

inActiveUser(id: number) {
  let header = new HttpHeaders();
  header.append("Authorization","Bearer "+ localStorage.getItem("token"));
 return this.httpClient.get(this.url3.concat(id+""),{ 'headers': header });
}

activeUser(id: number) {
  let header = new HttpHeaders();
  header.append("Authorization","Bearer "+ localStorage.getItem("token"));
  return this.httpClient.get(this.url3.concat("active/"+id+""),{ 'headers': header });
}


}