import { Component, OnInit } from '@angular/core';
import {PurchaseService} from "../shared/services/purchase.service";
import {Purchase} from "../shared/models/purchase.model";

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styleUrls: ['./purchases.component.css']
})
export class PurchasesComponent implements OnInit {

  purchases: Purchase[]

  constructor(private purchaseService: PurchaseService) { }

  ngOnInit(): void {
    this.purchaseService.getPurchases().subscribe(purchases => this.purchases = purchases)
  }

}
