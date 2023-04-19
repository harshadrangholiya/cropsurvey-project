import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CropdataComponent } from './cropdata.component';

describe('CropdataComponent', () => {
  let component: CropdataComponent;
  let fixture: ComponentFixture<CropdataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CropdataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CropdataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
