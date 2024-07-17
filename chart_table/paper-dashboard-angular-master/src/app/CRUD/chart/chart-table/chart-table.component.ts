import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import { ChartService } from '../service/chart.service';

@Component({
  selector: 'app-chart-table',
  templateUrl: './chart-table.component.html',
  styleUrls: ['./chart-table.component.scss']
})
export class ChartTableComponent implements OnInit {
  Bonds: string = '';
  Equities: string = '';
  Total: string = '';

  constructor(private chartService: ChartService) { }

  ngOnInit(): void {
    this.chartService.getTableValues().subscribe(data => {
      this.Bonds = data.Bonds;
      this.Equities = data.Equities;
      this.Total = data.Total;
    });
  }
}

