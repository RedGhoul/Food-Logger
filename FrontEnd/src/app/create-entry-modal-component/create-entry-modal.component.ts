import { Component, OnInit } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { CreateEditFoodModalComponent } from '../create-edit-food-modal-component/create-edit-food-modal.component';
@Component({
  selector: 'app-create-entry-modal',
  templateUrl: './create-entry-modal.component.html',
  styleUrls: ['./create-entry-modal.component.css']
})
export class CreateFoodEntryModalComponent implements OnInit {
  isSubmitted = false;
  closeBtnName = 'Close';
  title = "Create New Food Entry";
  bsModalFoodCreateRef?: BsModalRef
  ngOnInit() {

  }

  constructor(
    private modalService: BsModalService) { }

  openCreateNewFood(): void {
    this.bsModalFoodCreateRef = this.modalService.show(CreateEditFoodModalComponent, { id: 2 });
    this.bsModalFoodCreateRef.content.closeBtnName = 'Close';
  }

  closeModal(): void {
    this.modalService.hide(1);
  }

}


