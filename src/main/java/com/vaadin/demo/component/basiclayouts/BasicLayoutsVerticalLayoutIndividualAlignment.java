package com.vaadin.demo.component.basiclayouts;

import com.vaadin.demo.DemoExporter; // hidden-source-line
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;

@Route("basic-layouts/vertical-layout-individual-alignment")
public class BasicLayoutsVerticalLayoutIndividualAlignment extends Div {

    private static class AlignmentOption {
        private final String label;
        private final FlexComponent.Alignment alignment;

        public AlignmentOption(String label,
                FlexComponent.Alignment alignment) {
            this.label = label;
            this.alignment = alignment;
        }

        public FlexComponent.Alignment getAlignment() {
            return alignment;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public BasicLayoutsVerticalLayoutIndividualAlignment() {
        // tag::layout[]
        Button button1 = new Button("Button 1");
        button1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout layout = new VerticalLayout();
        layout.add(button1);
        layout.add(new Button("Button 2"));
        layout.add(new Button("Button 3"));
        // end::layout[]

        List<AlignmentOption> layoutOptions = Arrays
                .asList(new AlignmentOption("Start (default)",
                                FlexComponent.Alignment.START),
                        new AlignmentOption("Center",
                                FlexComponent.Alignment.CENTER),
                        new AlignmentOption("End", FlexComponent.Alignment.END),
                        new AlignmentOption("Stretch",
                                FlexComponent.Alignment.STRETCH));

        List<AlignmentOption> itemOptions = Arrays
                .asList(new AlignmentOption("Auto (default)",
                                FlexComponent.Alignment.AUTO),
                        new AlignmentOption("Start",
                                FlexComponent.Alignment.START),
                        new AlignmentOption("Center",
                                FlexComponent.Alignment.CENTER),
                        new AlignmentOption("End", FlexComponent.Alignment.END),
                        new AlignmentOption("Stretch",
                                FlexComponent.Alignment.STRETCH));

        RadioButtonGroup<AlignmentOption> layoutRadioGroup = new RadioButtonGroup<>();
        layoutRadioGroup.setLabel("Layout alignment");
        layoutRadioGroup.setItems(layoutOptions);
        layoutRadioGroup.setValue(layoutOptions.get(0));
        // tag::eventhandler1[]
        layoutRadioGroup.addValueChangeListener(e -> {
            FlexComponent.Alignment alignment = e.getValue().getAlignment();
            layout.setAlignItems(alignment);
        });
        // end::eventhandler1[]

        RadioButtonGroup<AlignmentOption> itemRadioGroup = new RadioButtonGroup<>();
        itemRadioGroup.setLabel("Item 1: alignment");
        itemRadioGroup.setItems(itemOptions);
        itemRadioGroup.setValue(itemOptions.get(0));
        // tag::eventhandler2[]
        itemRadioGroup.addValueChangeListener(e -> {
            FlexComponent.Alignment alignment = e.getValue().getAlignment();
            layout.setAlignSelf(alignment, button1);
        });
        // end::eventhandler2[]

        this.setClassName("basic-layouts-example");

        this.add(layout, layoutRadioGroup, itemRadioGroup);
    }

    public static class Exporter extends // hidden-source-line
            DemoExporter<BasicLayoutsVerticalLayoutIndividualAlignment> { // hidden-source-line
    } // hidden-source-line
}
