import { Component, OnInit } from '@angular/core';
import { DataAum } from '../DataAum.model';
import { DataAumService } from '../services/data-aum.service';

@Component({
  selector: 'app-data-aum',
  templateUrl: './data-aum.component.html',
  styleUrls: ['./data-aum.component.scss']
})
export class DataAumComponent implements OnInit {

  id: string;
  date: string;
  assetType: string;
  client: string;
  value: string;
  dataAums: DataAum[];
  dataAum: DataAum;

  constructor(private dataAumService: DataAumService) { }

  ngOnInit(): void {
    // Initial load
    this.dataAums = [];
  }

  findAll(): void {
    this.dataAumService.findAll().subscribe(
      data => {
        this.dataAums = data;
      },
      error => {
        console.error('Error fetching data', error);
      }
    );
  }

  findById(): void {
    if (this.id) {
      this.dataAumService.findById(this.id).subscribe(
        data => {
          this.dataAum = data;
        },
        error => {
          console.error('Error fetching data by id', error);
        }
      );
    }
  }
   /*
  search(): void {
    // Clear the dataAums array before performing a new search
    this.dataAums = [];
    
    this.dataAumService.search(this.id, this.date, this.assetType, this.client, this.value).subscribe(
      data => {
        this.dataAums = data;
      },
      error => {
        console.error('Error fetching data', error);
      }
    );
  }*/
}
