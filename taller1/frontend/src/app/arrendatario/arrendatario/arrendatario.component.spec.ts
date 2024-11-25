import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArrendatarioComponent } from './arrendatario.component';

describe('ArrendatarioComponent', () => {
  let component: ArrendatarioComponent;
  let fixture: ComponentFixture<ArrendatarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArrendatarioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArrendatarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});