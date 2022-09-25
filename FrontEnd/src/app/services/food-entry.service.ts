import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import { IFood } from '../models/Food';
import { IFoodEntry } from '../models/FoodEntry';
import { FoodEntryType } from '../models/FoodEntryType.enum';
import { FoodStats } from '../models/FoodStats';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FoodEntryService {
  baseUrl = environment.baseUrl;
  dinnerEntries: BehaviorSubject<IFoodEntry[]> = new BehaviorSubject([] as IFoodEntry[]);
  breakfastEntries: BehaviorSubject<IFoodEntry[]> = new BehaviorSubject([] as IFoodEntry[]);
  snackEntries: BehaviorSubject<IFoodEntry[]> = new BehaviorSubject([] as IFoodEntry[]);
  lunchEntries: BehaviorSubject<IFoodEntry[]> = new BehaviorSubject([] as IFoodEntry[]);
  foodStats: BehaviorSubject<FoodStats> = new BehaviorSubject({
    totalProtein : 0,
    totalFats: 0,
    totalSodium: 0,
    totalCalories: 0
  } as FoodStats);

  constructor(public http: HttpClient) {}

  getFoodStats(): Observable<FoodStats> {
    return this.foodStats;
  }
  getDinnerFoodEntries(): Observable<IFoodEntry[]> {
    return this.dinnerEntries;
  }
  getLunchFoodEntries(): Observable<IFoodEntry[]> {
    return this.lunchEntries;
  }
  getSnackFoodEntries(): Observable<IFoodEntry[]> {
    return this.snackEntries;
  }
  getBreakfastFoodEntries(): Observable<IFoodEntry[]> {
    return this.breakfastEntries;
  }

  removeFoodEntryById(entryId: number, type: FoodEntryType): void {
    switch (type) {
      case FoodEntryType.Dinner:
        this.dinnerEntries.next(this.dinnerEntries.getValue().filter(x => x.id != entryId && x.entryType == type));
        break;
      case FoodEntryType.Breakfast:
        this.breakfastEntries.next(this.breakfastEntries.getValue().filter(x => x.id != entryId && x.entryType == type));
        break;
      case FoodEntryType.Lunch:
        this.lunchEntries.next(this.lunchEntries.getValue().filter(x => x.id != entryId && x.entryType == type));
        break;
      case FoodEntryType.Snack:
        this.snackEntries.next(this.snackEntries.getValue().filter(x => x.id != entryId && x.entryType == type));
        break;
    }
    this.getTotalFoodStatsForDay();
  }

  addFoodEntry(food: IFood, type: FoodEntryType): void {
    let curFoodEntry = {
      id: 0,
      food: food,
      userId: 22,
      entryType: type
    } as IFoodEntry;
    switch (type) {
      case FoodEntryType.Dinner:
        this.create(curFoodEntry).subscribe(data => {
          if(data.ok && data.body != null){
            let newValDinner = this.dinnerEntries.getValue();
            newValDinner.push(data.body);
            this.dinnerEntries.next(newValDinner);
          }
        });
        break;
      case FoodEntryType.Breakfast:
        this.create(curFoodEntry).subscribe(data => {
          if(data.ok && data.body != null){
            let newValBreakfast = this.breakfastEntries.getValue();
            newValBreakfast.push(data.body);
            this.breakfastEntries.next(newValBreakfast);
          }
        });
        break;
      case FoodEntryType.Lunch:
        this.create(curFoodEntry).subscribe(data => {
          if(data.ok && data.body != null){
            let newValLunch = this.lunchEntries.getValue();
            newValLunch.push(data.body);
            this.lunchEntries.next(newValLunch);
          }
        });
        break;
      case FoodEntryType.Snack:
        this.create(curFoodEntry).subscribe(data => {
          if(data.ok && data.body != null){
            let newValSnack = this.snackEntries.getValue();
            newValSnack.push(data.body);
            this.snackEntries.next(newValSnack);
          }
        });
        break;
    }
    this.getTotalFoodStatsForDay();
  }

  getTotalFoodStatsForDay() {
    let total = {
      totalProtein : 0,
      totalFats: 0,
      totalSodium: 0,
      totalCalories: 0
    } as FoodStats;
    this.dinnerEntries.getValue().forEach(this.sumFoodTotalsFromFoodEntry(total));
    this.breakfastEntries.getValue().forEach(this.sumFoodTotalsFromFoodEntry(total));
    this.lunchEntries.getValue().forEach(this.sumFoodTotalsFromFoodEntry(total));
    this.snackEntries.getValue().forEach(this.sumFoodTotalsFromFoodEntry(total));
    this.foodStats.next(total);
  }

  create(food: IFoodEntry): Observable<HttpResponse<IFoodEntry>> {
    return this.http.post<IFoodEntry>(this.baseUrl + '/foods', food, { observe: 'response' });
  }

  update(food: IFoodEntry): Observable<HttpResponse<IFoodEntry>> {
    return this.http.put<IFoodEntry>(`${this.baseUrl}/${food.id}`, food, { observe: 'response' });
  }

  private sumFoodTotalsFromFoodEntry(total: FoodStats) {
    return (element: IFoodEntry) => {
      console.log(element)
      if (this.foodStats != null) {
        total.totalProtein += element.food.proteins!;
        total.totalCalories += element.food.calories!;
        total.totalFats += element.food.fat!;
        total.totalSodium += element.food.sodium!;
      }
    };
  }
}
