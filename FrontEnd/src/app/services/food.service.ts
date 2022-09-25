import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {IFood} from "../models/Food";
import {HttpClient, HttpResponse} from '@angular/common/http';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  baseUrl = environment.baseUrl;

  constructor(public http: HttpClient) {}

  getFoodsPaged(page: number, pageSize: number): Observable<HttpResponse<IFood[]>>{
    return this.http.get<IFood[]>(
      this.baseUrl + `/foods?eagerload=true&sort=id%2Casc&page=${page-1}&size=${pageSize}`,
      { observe: 'response' });
  }

  getAllFoods(): Observable<HttpResponse<IFood[]>>{
    return this.http.get<IFood[]>(
      this.baseUrl + `/foods?eagerload=true&sort=id%2Casc&page=0&size=20`,
      { observe: 'response' });
  }

  create(food: IFood): Observable<HttpResponse<IFood>> {
    return this.http.post<IFood>(this.baseUrl + '/foods', food, { observe: 'response' });
  }

  update(food: IFood): Observable<HttpResponse<IFood>> {
    return this.http.put<IFood>(`${this.baseUrl}/${food.id}`, food, { observe: 'response' });
  }

}
