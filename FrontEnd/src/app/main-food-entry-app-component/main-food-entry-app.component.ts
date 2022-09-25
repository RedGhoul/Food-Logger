import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef, ModalOptions } from 'ngx-bootstrap/modal';
import { CreateFoodEntryModalComponent } from '../create-entry-modal-component/create-entry-modal.component';
import { IFoodEntry } from '../models/FoodEntry';
import {FoodStats} from '../models/FoodStats';
import { FoodEntryService } from '../services/food-entry.service';

@Component({
  selector: 'app-main-food-entry-component',
  templateUrl: './main-food-entry-app.component.html',
  styleUrls: ['./main-food-entry-app.component.css']
})
export class MainFoodEntryAppComponent implements OnInit {
  today = new Date().toISOString().slice(0, 10)
  bsModalRef?: BsModalRef;
  dinnerEntries: IFoodEntry[] = [];
  breakfastEntries: IFoodEntry[] = [];
  snackEntries: IFoodEntry[] = [];
  lunchEntries: IFoodEntry[] = [];
  foodStats: FoodStats = new FoodStats();
  constructor(private modalService: BsModalService,
    private foodEntryService: FoodEntryService) { }

  ngOnInit(): void {
    // changes made to array behind this
    // automatically reflect in the ui
    this.foodEntryService.getDinnerFoodEntries()
      .subscribe(data => this.dinnerEntries = data);

    this.foodEntryService.getLunchFoodEntries()
      .subscribe(data => this.lunchEntries = data);

    this.foodEntryService.getSnackFoodEntries()
      .subscribe(data => this.snackEntries = data);

    this.foodEntryService.getBreakfastFoodEntries()
      .subscribe(data => this.breakfastEntries = data);

    this.foodEntryService.getFoodStats()
      .subscribe(data => this.foodStats = data);
  }


  openAddFoodEntryModal() {
    this.bsModalRef = this.modalService.show(CreateFoodEntryModalComponent, { id: 1 });
    this.bsModalRef.content.closeBtnName = 'Close';
  }

}
