import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IFood} from '../models/Food';
import {TypeaheadMatch} from 'ngx-bootstrap/typeahead';
import {FoodEntryService} from '../services/food-entry.service';
import {FoodEntryType} from '../models/FoodEntryType.enum';
import {FoodService} from "../services/food.service";

@Component({
  selector: 'app-food-entry-form',
  templateUrl: './food-entry-form.component.html',
  styleUrls: ['./food-entry-form.component.css']
})
export class FoodEntryComponent implements OnInit {
  @Output() finishedFormSubmission = new EventEmitter<void>();

  selectedTypeAheadFood?: IFood;
  selectedFood?: IFood;
  listOfFoods: IFood[] = [];

  currentFoodEntry: FoodEntryType = FoodEntryType.Breakfast;
  listOfFoodEntryTypes: FoodEntryType[] = Object.values(FoodEntryType);

  constructor(
    public http: HttpClient,
    private foodEntryService: FoodEntryService,
    private foodService: FoodService
  ) {
  }

  ngOnInit(): void {
    this.foodService.getAllFoods().subscribe(data => {
      data.body?.forEach(x => {
        this.listOfFoods?.push(x)
      });
    });
  }

  onSelect(event: TypeaheadMatch): void {
    this.selectedFood = event.item as IFood;
  }

  onSubmit(): void {
    if (this.selectedFood) {
      this.foodEntryService.addFoodEntry(
        this.selectedFood, this.currentFoodEntry);
      this.finishedFormSubmission.emit();
    }
  }
}
