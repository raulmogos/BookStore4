import { Component, OnInit } from '@angular/core';
import {Client} from "../../shared/models/client.model";
import {ClientService} from "../../shared/services/client.service";

@Component({
  selector: 'app-add-new-client',
  templateUrl: './add-new-client.component.html',
  styleUrls: ['./add-new-client.component.css']
})
export class AddNewClientComponent implements OnInit {

  newClient: Client

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.newClient = new Client()
    this.newClient.moneySpent = 0
  }

  addOnSubmit() {
    // validation
    this.clientService.addNewClient(this.newClient)
    this.newClient.firstName = '';
    this.newClient.lastName = '';
  }

  firstNameOnchange(event) {
    this.newClient.firstName = event.target.value;
  }

  lastNameOnchange(event) {
    this.newClient.lastName = event.target.value;
  }
}
