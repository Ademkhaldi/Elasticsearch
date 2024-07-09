import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchDataAumComponent } from './search-data-aum.component';

describe('SearchDataAumComponent', () => {
  let component: SearchDataAumComponent;
  let fixture: ComponentFixture<SearchDataAumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchDataAumComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchDataAumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
