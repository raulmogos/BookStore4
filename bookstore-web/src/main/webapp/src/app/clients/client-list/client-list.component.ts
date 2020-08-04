import { Component, OnInit } from '@angular/core';
import {ClientService} from "../../shared/services/client.service";
import {Client} from "../../shared/models/client.model";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  clients: Client[]

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.getClients()
  }

  getClients(): void {
    this.clientService.getClients().subscribe(
      clients => {
        this.clients = clients
        this.clients.sort((c1, c2) => c1.id - c2.id)
      }
    )
  }

}
