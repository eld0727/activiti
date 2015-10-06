package activiti.model.stencil.defaultset.stencils;

import activiti.model.stencil.Stencil;
import activiti.model.stencil.defaultset.properties.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 01.10.2015.<br/>
 * TODO comment me
 */
@Component
@Order(400)
public class BoundaryEventsStencilsProvider implements StencilProvider {

    @Autowired
    private DefaultProperties defaultProperties;

    private Stencil boundaryErrorEvent;
    private Stencil boundaryTimerEvent;
    private Stencil boundarySignalEvent;
    private Stencil boundaryMessageEvent;
    private Stencil boundaryCancelEvent;
    private Stencil boundaryCompensationEvent;

    @PostConstruct
    private void postConstruct() {
        boundaryErrorEvent = Stencil.builder()
                .id("BoundaryErrorEvent")
                .type(Stencil.NODE)
                .title("Boundary error event")
                .description("A boundary event that catches a BPMN error")
                .view("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<svg\n   xmlns=\"http://www.w3.org/2000/svg\"\n   xmlns:oryx=\"http://www.b3mn.org/oryx\"\n   width=\"40\"\n   height=\"40\"\n   version=\"1.0\">\n  <defs></defs>\n  <oryx:magnets>\n  \t<oryx:magnet oryx:cx=\"16\" oryx:cy=\"16\" oryx:default=\"yes\" />\n  </oryx:magnets>\n  <oryx:docker oryx:cx=\"16\" oryx:cy=\"16\" />\n  <g pointer-events=\"fill\">\n    <circle id=\"bg_frame\" cx=\"16\" cy=\"16\" r=\"15\" stroke=\"#585858\" fill=\"#ffffff\" stroke-width=\"1\"/>\n    <circle id=\"frame\" cx=\"16\" cy=\"16\" r=\"12\" stroke=\"#585858\" fill=\"none\" stroke-width=\"1\"/>\n    \n    <path\n         stroke=\"#585858\"\n         style=\"fill:none;stroke-width:1.5;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:10\"\n         d=\"M 22.820839,11.171502 L 19.36734,24.58992 L 13.54138,14.281819 L 9.3386512,20.071607 L 13.048949,6.8323057 L 18.996148,16.132659 L 22.820839,11.171502 z\"\n         id=\"errorPolygon\" />\n\t<text font-size=\"11\" \n\t\tid=\"text_name\" \n\t\tx=\"16\" y=\"33\" \n\t\toryx:align=\"top center\" \n\t\tstroke=\"#373e48\"\n\t></text>\n  </g>\n</svg>")
                .icon("catching/error.png")
                .group("Boundary Events")
                .property(defaultProperties.overrideidpackage())
                .property(defaultProperties.namepackage())
                .property(defaultProperties.documentationpackage())
                .property(defaultProperties.errorrefpackage())
                .roles(Arrays.asList("sequence_start", "BoundaryEventsMorph", "IntermediateEventOnActivityBoundary"))
                .build();

        boundaryTimerEvent = Stencil.builder()
                .id("BoundaryTimerEvent")
                .type(Stencil.NODE)
                .title("Boundary timer event")
                .description("A boundary event with a timer trigger")
                .view("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<svg\n   xmlns=\"http://www.w3.org/2000/svg\"\n   xmlns:oryx=\"http://www.b3mn.org/oryx\"\n   width=\"40\"\n   height=\"40\"\n   version=\"1.0\">\n  <defs></defs>\n  <oryx:magnets>\n  \t<oryx:magnet oryx:cx=\"16\" oryx:cy=\"16\" oryx:default=\"yes\" />\n  </oryx:magnets>\n  <oryx:docker oryx:cx=\"16\" oryx:cy=\"16\" />\n  <g pointer-events=\"fill\">\n    <circle \n    \tid=\"bg_frame\" \n    \tcx=\"16\" \n    \tcy=\"16\" \n    \tr=\"15\" \n    \tstroke=\"#585858\" \n    \tfill=\"#ffffff\" \n    \tstroke-width=\"1\"\n    \tstyle=\"stroke-dasharray: 5.5, 3\" />\n    \t\n    <circle \n    \tid=\"frame2_non_interrupting\" \n    \tcx=\"16\" \n    \tcy=\"16\" \n    \tr=\"12\" \n    \tstroke=\"#585858\" \n    \tfill=\"none\" \n    \tstroke-width=\"1\"\n    \tstyle=\"stroke-dasharray: 4.5, 3\" />\n    \n    <circle id=\"frame\" cx=\"16\" cy=\"16\" r=\"15\" stroke=\"#585858\" fill=\"none\" stroke-width=\"1\"/>\n    <circle id=\"frame2\" cx=\"16\" cy=\"16\" r=\"12\" stroke=\"#585858\" fill=\"none\" stroke-width=\"1\"/>\n    \n    <path id=\"path1\" transform=\"translate(6,6)\"\n    \td=\"M 10 0 C 4.4771525 0 0 4.4771525 0 10 C 0 15.522847 4.4771525 20 10 20 C 15.522847 20 20 15.522847 20 10 C 20 4.4771525 15.522847 1.1842379e-15 10 0 z M 9.09375 1.03125 C 9.2292164 1.0174926 9.362825 1.0389311 9.5 1.03125 L 9.5 3.5 L 10.5 3.5 L 10.5 1.03125 C 15.063526 1.2867831 18.713217 4.9364738 18.96875 9.5 L 16.5 9.5 L 16.5 10.5 L 18.96875 10.5 C 18.713217 15.063526 15.063526 18.713217 10.5 18.96875 L 10.5 16.5 L 9.5 16.5 L 9.5 18.96875 C 4.9364738 18.713217 1.2867831 15.063526 1.03125 10.5 L 3.5 10.5 L 3.5 9.5 L 1.03125 9.5 C 1.279102 5.0736488 4.7225326 1.4751713 9.09375 1.03125 z M 9.5 5 L 9.5 8.0625 C 8.6373007 8.2844627 8 9.0680195 8 10 C 8 11.104569 8.8954305 12 10 12 C 10.931981 12 11.715537 11.362699 11.9375 10.5 L 14 10.5 L 14 9.5 L 11.9375 9.5 C 11.756642 8.7970599 11.20294 8.2433585 10.5 8.0625 L 10.5 5 L 9.5 5 z \"  \n    \tfill=\"#585858\" stroke=\"none\" />\n    \t\n\t<text font-size=\"11\" \n\t\tid=\"text_name\" \n\t\tx=\"16\" y=\"33\" \n\t\toryx:align=\"top center\" \n\t\tstroke=\"#373e48\"\n\t></text>\n  </g>\n</svg>")
                .icon("catching/timer.png")
                .group("Boundary Events")
                .property(defaultProperties.overrideidpackage())
                .property(defaultProperties.namepackage())
                .property(defaultProperties.documentationpackage())
                .property(defaultProperties.timercycledefinitionpackage())
                .property(defaultProperties.timerdatedefinitionpackage())
                .property(defaultProperties.timerdurationdefinitionpackage())
                .property(defaultProperties.timerenddatedefinitionpackage())
                .roles(Arrays.asList("sequence_start", "BoundaryEventsMorph", "IntermediateEventOnActivityBoundary"))
                .build();

        boundarySignalEvent = Stencil.builder()
                .id("BoundarySignalEvent")
                .type(Stencil.NODE)
                .title("Boundary signal event")
                .description("A boundary event with a signal trigger")
                .view("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<svg\n   xmlns=\"http://www.w3.org/2000/svg\"\n   xmlns:oryx=\"http://www.b3mn.org/oryx\"\n   width=\"40\"\n   height=\"40\"\n   version=\"1.0\">\n  <defs></defs>\n  <oryx:magnets>\n  \t<oryx:magnet oryx:cx=\"16\" oryx:cy=\"16\" oryx:default=\"yes\" />\n  </oryx:magnets>\n  <oryx:docker oryx:cx=\"16\" oryx:cy=\"16\" />\n  <g pointer-events=\"fill\">\n    <circle \n    \tid=\"bg_frame\" \n    \tcx=\"16\" \n    \tcy=\"16\" \n    \tr=\"15\" \n    \tstroke=\"#585858\" \n    \tfill=\"#ffffff\" \n    \tstroke-width=\"1\"\n    \tstyle=\"stroke-dasharray: 5.5, 3\" />\n    \t\n    <circle \n    \tid=\"frame2_non_interrupting\" \n    \tcx=\"16\" \n    \tcy=\"16\" \n    \tr=\"12\" \n    \tstroke=\"#585858\" \n    \tfill=\"none\" \n    \tstroke-width=\"1\"\n    \tstyle=\"stroke-dasharray: 4.5, 3\" />\n    \n    <circle id=\"frame\" cx=\"16\" cy=\"16\" r=\"15\" stroke=\"#585858\" fill=\"none\" stroke-width=\"1\"/>\n    <circle id=\"frame2\" cx=\"16\" cy=\"16\" r=\"12\" stroke=\"#585858\" fill=\"none\" stroke-width=\"1\"/>\n\t<path\n\t   id=\"signalCatching\"\n\t   stroke=\"#585858\"\n       d=\"M 8.7124971,21.247342 L 23.333334,21.247342 L 16.022915,8.5759512 L 8.7124971,21.247342 z\"\n       style=\"fill:none;stroke-width:1.4;stroke-miterlimit:4;stroke-dasharray:none\" />\n\t<text font-size=\"11\" \n\t\tid=\"text_name\" \n\t\tx=\"16\" y=\"33\" \n\t\toryx:align=\"top center\" \n\t\tstroke=\"#373e48\"\n\t></text>\n  </g>\n</svg>")
                .icon("catching/signal.png")
                .group("Boundary Events")
                .property(defaultProperties.overrideidpackage())
                .property(defaultProperties.namepackage())
                .property(defaultProperties.documentationpackage())
                .property(defaultProperties.signalrefpackage())
                .property(defaultProperties.cancelactivitypackage())
                .roles(Arrays.asList("sequence_start", "BoundaryEventsMorph", "IntermediateEventOnActivityBoundary"))
                .build();

        boundaryMessageEvent = Stencil.builder()
                .id("BoundaryMessageEvent")
                .type(Stencil.NODE)
                .title("Boundary message event")
                .description("A boundary event with a message trigger")
                .view("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<svg\n   xmlns=\"http://www.w3.org/2000/svg\"\n   xmlns:oryx=\"http://www.b3mn.org/oryx\"\n   width=\"40\"\n   height=\"40\"\n   version=\"1.0\">\n  <defs></defs>\n  <oryx:magnets>\n  \t<oryx:magnet oryx:cx=\"16\" oryx:cy=\"16\" oryx:default=\"yes\" />\n  </oryx:magnets>\n  <oryx:docker oryx:cx=\"16\" oryx:cy=\"16\" />\n  <g pointer-events=\"fill\">\n    <circle \n    \tid=\"bg_frame\" \n    \tcx=\"16\" \n    \tcy=\"16\" \n    \tr=\"15\" \n    \tstroke=\"#585858\" \n    \tfill=\"#ffffff\" \n    \tstroke-width=\"1\"\n    \tstyle=\"stroke-dasharray: 5.5, 3\" />\n    \t\n    <circle \n    \tid=\"frame2_non_interrupting\" \n    \tcx=\"16\" \n    \tcy=\"16\" \n    \tr=\"12\" \n    \tstroke=\"#585858\" \n    \tfill=\"none\" \n    \tstroke-width=\"1\"\n    \tstyle=\"stroke-dasharray: 4.5, 3\" />\n    \t\n    <circle id=\"frame\" cx=\"16\" cy=\"16\" r=\"15\" stroke=\"#585858\" fill=\"none\" stroke-width=\"1\"/>\n    <circle id=\"frame2\" cx=\"16\" cy=\"16\" r=\"12\" stroke=\"#585858\" fill=\"none\" stroke-width=\"1\"/>\n    \n\t<g id=\"messageCatching\">\n\t\t<path transform=\"translate(7,7)\" id=\"path1\" stroke=\"none\" fill=\"#585858\" stroke-width=\"1\" d=\"M 1 3 L 9 11 L 17 3 L 1 3 z M 1 5 L 1 13 L 5 9 L 1 5 z M 17 5 L 13 9 L 17 13 L 17 5 z M 6 10 L 1 15 L 17 15 L 12 10 L 9 13 L 6 10 z \"/>\n\t</g>\n\t<text font-size=\"11\" \n\t\tid=\"text_name\" \n\t\tx=\"16\" y=\"33\" \n\t\toryx:align=\"top center\" \n\t\tstroke=\"#373e48\"\n\t></text>\n\t\n  </g>\n</svg>")
                .icon("catching/message.png")
                .group("Boundary Events")
                .property(defaultProperties.overrideidpackage())
                .property(defaultProperties.namepackage())
                .property(defaultProperties.documentationpackage())
                .property(defaultProperties.messagerefpackage())
                .property(defaultProperties.cancelactivitypackage())
                .roles(Arrays.asList("sequence_start", "BoundaryEventsMorph", "IntermediateEventOnActivityBoundary"))
                .build();

        boundaryCancelEvent = Stencil.builder()
                .id("BoundaryCancelEvent")
                .type(Stencil.NODE)
                .title("Boundary cancel event")
                .description("A boundary cancel event")
                .view("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<svg\n   xmlns=\"http://www.w3.org/2000/svg\"\n   xmlns:oryx=\"http://www.b3mn.org/oryx\"\n   width=\"40\"\n   height=\"40\"\n   version=\"1.0\">\n  <defs></defs>\n  <oryx:magnets>\n  \t<oryx:magnet oryx:cx=\"16\" oryx:cy=\"16\" oryx:default=\"yes\" />\n  </oryx:magnets>\n  <oryx:docker oryx:cx=\"16\" oryx:cy=\"16\" />\n  <g pointer-events=\"fill\">\n  \n    <circle id=\"bg_frame\" cx=\"16\" cy=\"16\" r=\"15\" stroke=\"#585858\" fill=\"#ffffff\" stroke-width=\"1\"/>\n    <circle id=\"frame\" cx=\"16\" cy=\"16\" r=\"12\" stroke=\"#585858\" fill=\"none\" stroke-width=\"1\"/>\n    \n    <path\n       d=\"M 7.2839105,10.27369 L 10.151395,7.4062062 L 15.886362,13.141174 L 21.621331,7.4062056 L 24.488814,10.273689 L 18.753846,16.008657 L 24.488815,21.743626 L 21.621331,24.611111 L 15.886362,18.876142 L 10.151394,24.611109 L 7.283911,21.743625 L 13.018878,16.008658 L 7.2839105,10.27369 z\"\n       id=\"cancelCross\" fill=\"none\" stroke=\"#585858\" stroke-width=\"1.7\" />\n\t<text font-size=\"11\" \n\t\tid=\"text_name\" \n\t\tx=\"16\" y=\"33\" \n\t\toryx:align=\"top center\" \n\t\tstroke=\"#373e48\"\n\t></text>\n  </g>\n</svg>")
                .icon("catching/cancel.png")
                .group("Boundary Events")
                .property(defaultProperties.overrideidpackage())
                .property(defaultProperties.namepackage())
                .property(defaultProperties.documentationpackage())
                .roles(Arrays.asList("sequence_start", "BoundaryEventsMorph", "IntermediateEventOnActivityBoundary"))
                .build();

        boundaryCompensationEvent = Stencil.builder()
                .id("BoundaryCompensationEvent")
                .type(Stencil.NODE)
                .title("Boundary compensation event")
                .description("A boundary compensation event")
                .view("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<svg\n   xmlns=\"http://www.w3.org/2000/svg\"\n   xmlns:oryx=\"http://www.b3mn.org/oryx\"\n   width=\"40\"\n   height=\"40\"\n   version=\"1.0\">\n  <defs></defs>\n  <oryx:magnets>\n  \t<oryx:magnet oryx:cx=\"16\" oryx:cy=\"16\" oryx:default=\"yes\" />\n  </oryx:magnets>\n  <oryx:docker oryx:cx=\"16\" oryx:cy=\"16\" />\n  <g pointer-events=\"fill\">\n\t\n    <circle id=\"bg_frame\" cx=\"16\" cy=\"16\" r=\"15\" stroke=\"#585858\" fill=\"#ffffff\" stroke-width=\"1\"/>\n    <circle id=\"frame\" cx=\"16\" cy=\"16\" r=\"12\" stroke=\"#585858\" fill=\"#ffffff\" stroke-width=\"1\"/>\n    \n    <polygon id=\"poly1\" stroke=\"#585858\" fill=\"none\" stroke-width=\"1.4\" points=\"15,9 15,23 8,16\" stroke-linecap=\"butt\" stroke-linejoin=\"miter\" stroke-miterlimit=\"10\" />\n    <polygon id=\"poly2\" stroke=\"#585858\" fill=\"none\" stroke-width=\"1.4\" points=\"22,9 22,23 15,16\" stroke-linecap=\"butt\" stroke-linejoin=\"miter\" stroke-miterlimit=\"10\" />\n\t<text font-size=\"11\" \n\t\tid=\"text_name\" \n\t\tx=\"16\" y=\"33\" \n\t\toryx:align=\"top center\" \n\t\tstroke=\"#373e48\"\n\t></text>\n </g>\n</svg>")
                .icon("catching/compensation.png")
                .group("Boundary Events")
                .property(defaultProperties.overrideidpackage())
                .property(defaultProperties.namepackage())
                .property(defaultProperties.documentationpackage())
                .roles(Arrays.asList("BoundaryEventsMorph", "IntermediateEventOnActivityBoundary", "all"))
                .build();
    }

    @Override
    public List<Stencil> getAll() {
        return Arrays.asList(
                boundaryErrorEvent,
                boundaryTimerEvent,
                boundarySignalEvent,
                boundaryMessageEvent,
                boundaryCancelEvent,
                boundaryCompensationEvent
        );
    }
}
