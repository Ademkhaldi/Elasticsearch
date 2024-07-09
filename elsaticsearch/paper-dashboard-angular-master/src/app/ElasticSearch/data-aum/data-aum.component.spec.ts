import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataAumComponent } from './data-aum.component';

describe('DataAumComponent', () => {
  let component: DataAumComponent;
  let fixture: ComponentFixture<DataAumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DataAumComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DataAumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
