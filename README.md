<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>vaya — one map, two sides of every ride</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@500;600;700&family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
<style>
  :root{
    --ink:#15161A;
    --ink-soft:#55575F;
    --paper:#FAF9F6;
    --paper-dim:#F1F0EB;
    --line:#E4E1D9;

    --rider:#4B3AE0;
    --rider-deep:#2C1F94;
    --rider-tint:#EEECFB;
    --rider-tint-2:#E1DDF8;

    --driver:#0B6E4F;
    --driver-deep:#083F2E;
    --driver-tint:#E2F5EA;
    --driver-tint-2:#CFEEDD;

    --radius-lg: 28px;
  }

  *{ box-sizing:border-box; margin:0; padding:0; }
  html{ scroll-behavior:smooth; }
  body{
    background:var(--paper);
    color:var(--ink);
    font-family:'Inter', sans-serif;
    -webkit-font-smoothing:antialiased;
    overflow-x:hidden;
  }
  h1,h2,h3{
    font-family:'Space Grotesk', sans-serif;
    color:var(--ink);
    line-height:1.05;
    letter-spacing:-0.01em;
  }
  p{ color:var(--ink-soft); line-height:1.6; }
  a{ color:inherit; text-decoration:none; }
  img,svg{ display:block; }
  .wrap{ max-width:1180px; margin:0 auto; padding:0 32px; }

  ::selection{ background:var(--rider); color:#fff; }

  /* ---------- NAV ---------- */
  nav{
    position:sticky; top:0; z-index:50;
    background:rgba(250,249,246,0.82);
    backdrop-filter:blur(10px);
    border-bottom:1px solid var(--line);
  }
  nav .wrap{
    height:72px; display:flex; align-items:center; justify-content:space-between;
  }
  .logo{
    font-family:'Space Grotesk', sans-serif;
    font-weight:700; font-size:22px; color:var(--ink);
    display:flex; align-items:center; gap:2px;
  }
  .logo .dot{
    width:8px; height:8px; border-radius:50%;
    background:linear-gradient(135deg, var(--rider), var(--driver));
    margin-left:2px;
  }
  .nav-links{ display:flex; gap:36px; }
  .nav-links a{
    font-size:14.5px; font-weight:500; color:var(--ink-soft);
    transition:color .15s ease;
  }
  .nav-links a:hover{ color:var(--ink); }
  .nav-cta{
    font-size:14px; font-weight:600; color:#fff;
    background:var(--ink); padding:10px 20px; border-radius:999px;
    white-space:nowrap;
  }
  @media (max-width:820px){ .nav-links{ display:none; } }

  /* ---------- BUTTONS ---------- */
  .btn{
    display:inline-flex; align-items:center; justify-content:center;
    font-weight:600; font-size:15px; border-radius:999px;
    padding:14px 26px; border:1.5px solid transparent; cursor:pointer;
    transition:transform .15s ease, box-shadow .15s ease;
  }
  .btn:hover{ transform:translateY(-1px); }
  .btn-rider{ background:var(--rider); color:#fff; box-shadow:0 10px 24px -10px rgba(75,58,224,.55); }
  .btn-driver{ background:var(--driver); color:#fff; box-shadow:0 10px 24px -10px rgba(11,110,79,.5); }
  .btn-outline{ background:transparent; color:var(--ink); border-color:var(--ink); }

  /* ---------- HERO ---------- */
  .hero{
    position:relative;
    padding:96px 0 40px;
    background:
      radial-gradient(circle at 14% 20%, var(--rider-tint) 0%, transparent 46%),
      radial-gradient(circle at 88% 12%, var(--driver-tint) 0%, transparent 40%);
    overflow:hidden;
  }
  .hero .wrap{
    display:grid; grid-template-columns:1.05fr 0.95fr; gap:40px; align-items:center;
  }
  .hero-copy{ animation:riseIn .8s cubic-bezier(.2,.8,.2,1) both; }
  .hero-copy h1{ font-size:clamp(34px,4.6vw,54px); max-width:11.5ch; }
  .hero-copy p.lead{ margin-top:20px; font-size:18px; max-width:44ch; }
  .hero-ctas{ display:flex; gap:14px; margin-top:32px; flex-wrap:wrap; }
  .hero-note{ margin-top:22px; font-size:13.5px; color:var(--ink-soft); }
  .hero-note b{ color:var(--ink); }

  @keyframes riseIn{
    from{ opacity:0; transform:translateY(18px); }
    to{ opacity:1; transform:translateY(0); }
  }
  .hero-phone-wrap{
    display:flex; justify-content:center;
    animation:riseIn .9s .15s cubic-bezier(.2,.8,.2,1) both;
  }

  @media (max-width:900px){
    .hero .wrap{ grid-template-columns:1fr; text-align:left; }
    .hero-phone-wrap{ order:-1; margin-bottom:8px; }
  }

  /* ---------- PHONE MOCKUP ---------- */
  .phone-shell{ display:flex; flex-direction:column; align-items:center; }
  .phone{
    --pw:250px; --ph:522px;
    width:var(--pw); height:var(--ph);
    border-radius:52px;
    background:linear-gradient(155deg,#3b3c40 0%, #17181b 45%, #050506 100%);
    padding:11px;
    position:relative;
    box-shadow:
      0 2px 0 rgba(255,255,255,.15) inset,
      0 40px 70px -30px rgba(15,16,20,.55),
      0 14px 28px -12px rgba(15,16,20,.35);
    flex-shrink:0;
  }
  .phone.large{ --pw:288px; --ph:600px; }
  .phone::before{ /* power button */
    content:''; position:absolute; right:-3px; top:150px; width:3px; height:70px;
    background:#0c0c0d; border-radius:2px 0 0 2px;
  }
  .phone::after{ /* volume buttons */
    content:''; position:absolute; left:-3px; top:120px; width:3px; height:38px;
    background:#0c0c0d; border-radius:0 2px 2px 0;
    box-shadow: 0 56px 0 #0c0c0d;
  }
  .screen{
    position:relative; width:100%; height:100%;
    border-radius:42px; overflow:hidden;
    background:#fff;
    display:flex; flex-direction:column;
  }
  .dyn-island{
    position:absolute; top:14px; left:50%; transform:translateX(-50%);
    width:96px; height:28px; background:#000; border-radius:20px; z-index:20;
  }
  .home-ind{
    position:absolute; bottom:8px; left:50%; transform:translateX(-50%);
    width:128px; height:4.5px; border-radius:3px; z-index:20;
    background:rgba(0,0,0,.55);
  }
  .home-ind.on-dark{ background:rgba(255,255,255,.75); }

  .status-bar{
    display:flex; align-items:center; justify-content:space-between;
    padding:16px 26px 0; font-size:14px; font-weight:600; color:var(--ink);
    position:relative; z-index:15;
  }
  .status-icons{ display:flex; align-items:center; gap:5px; }

  .screen-body{ flex:1; position:relative; display:flex; flex-direction:column; }

  /* login screen */
  .s-login{ padding:16px 22px 26px; display:flex; flex-direction:column; height:100%; }
  .s-login .mark{ width:38px; height:38px; border-radius:11px; }
  .s-login .center{ flex:1; display:flex; flex-direction:column; align-items:center; justify-content:center; gap:8px; text-align:center; }
  .s-login h4{ font-family:'Space Grotesk'; font-size:19px; margin-top:14px; }
  .s-login .sub{ font-size:11.5px; color:var(--ink-soft); }
  .field{
    width:100%; height:44px; background:var(--paper-dim); border-radius:999px;
    display:flex; align-items:center; padding:0 16px; font-size:13px; color:#9a988f;
    margin-bottom:10px;
  }
  .cta-full{
    width:100%; height:48px; border-radius:999px; display:flex; align-items:center;
    justify-content:center; color:#fff; font-size:13.5px; font-weight:600;
  }

  /* map bg */
  .map{
    position:absolute; inset:0;
    background-image:radial-gradient(rgba(0,0,0,.07) 1px, transparent 1.4px);
    background-size:15px 15px;
  }
  .map-rider{ background-color:var(--rider-tint); }
  .map-driver{ background-color:var(--driver-tint); }
  .road{ position:absolute; opacity:.55; }

  .pin{
    position:absolute; width:10px; height:10px; border-radius:50%;
    border:2.5px solid #fff; box-shadow:0 2px 6px rgba(0,0,0,.25);
  }
  .pin.you{ width:16px; height:16px; }
  .pin::after{ content:''; }

  .top-pill{
    position:absolute; top:16px; left:50%; transform:translateX(-50%);
    background:#fff; border:1px solid rgba(0,0,0,.08); border-radius:999px;
    padding:9px 16px; font-size:12px; font-weight:600; display:flex; align-items:center; gap:7px;
    box-shadow:0 6px 16px -8px rgba(0,0,0,.25); z-index:10;
  }
  .top-pill.dark{ background:#111; color:#fff; border:none; }
  .top-pill .dot-live{
    width:6px; height:6px; border-radius:50%; background:#3ee08b;
    animation:blink 1.1s ease-in-out infinite;
  }
  @keyframes blink{ 0%,100%{ opacity:1; } 50%{ opacity:.3; } }

  .avatar-btn{
    position:absolute; top:16px; right:16px; width:34px; height:34px; border-radius:50%;
    background:#fff; border:1px solid rgba(0,0,0,.08); z-index:10;
    display:flex; align-items:center; justify-content:center;
    box-shadow:0 6px 16px -8px rgba(0,0,0,.25);
  }

  .search-pill{
    position:absolute; top:16px; left:16px; right:56px;
    background:#fff; border:1px solid rgba(0,0,0,.08); border-radius:999px;
    height:38px; display:flex; align-items:center; gap:8px; padding:0 14px;
    font-size:12.5px; color:#9a988f; z-index:10;
    box-shadow:0 6px 16px -8px rgba(0,0,0,.25);
  }

  .sheet{
    position:absolute; left:0; right:0; bottom:0;
    background:#fff; border-radius:22px 22px 0 0;
    padding:14px 18px 30px;
    box-shadow:0 -10px 24px -18px rgba(0,0,0,.4);
  }
  .sheet .grabber{ width:32px; height:4px; border-radius:3px; background:var(--line); margin:0 auto 12px; }
  .sheet .label-sm{ font-size:10.5px; color:var(--ink-soft); margin-bottom:6px; }
  .sheet .row{ display:flex; justify-content:space-between; align-items:center; margin-bottom:10px; }
  .sheet .row .l{ font-size:12.5px; font-weight:600; }
  .sheet .row .r{ font-size:12.5px; font-weight:700; }

  .search-page{ padding:18px 18px 0; height:100%; }
  .search-page .head{ display:flex; align-items:center; gap:10px; margin-bottom:16px; }
  .search-page .input{
    flex:1; height:38px; border-radius:999px; background:var(--paper-dim);
    border:1.5px solid var(--ink); display:flex; align-items:center; padding:0 14px;
    font-size:12.5px; color:var(--ink);
  }
  .search-page h5{ font-size:11px; font-weight:600; color:var(--ink-soft); margin:6px 0 8px; }
  .result-row{
    display:flex; align-items:center; gap:10px; padding:10px 6px; font-size:12px;
    color:var(--ink-soft); border-top:1px solid var(--paper-dim);
  }
  .result-row:first-of-type{ border-top:none; }
  .result-row.active{ background:var(--paper-dim); border-radius:10px; color:var(--ink); font-weight:600; border-top:none; }
  .ico-dot{ width:16px; height:16px; flex-shrink:0; opacity:.7; }

  .btn-pair{ display:flex; gap:8px; }
  .btn-pair > div{
    flex:1; height:44px; border-radius:999px; display:flex; align-items:center;
    justify-content:center; font-size:12.5px; font-weight:600;
  }

  /* ---------- STEP LABEL ---------- */
  .step-label{
    margin-top:16px; display:flex; align-items:center; gap:9px;
    font-size:13.5px; font-weight:600; color:var(--ink);
  }
  .step-label .num{
    width:20px; height:20px; border-radius:50%; color:#fff; font-size:11px;
    display:flex; align-items:center; justify-content:center; flex-shrink:0;
  }
  .step-cap{ font-size:12px; color:var(--ink-soft); margin-top:2px; }

  /* ---------- SECTIONS ---------- */
  section.showcase{ padding:104px 0; }
  .sec-head{ max-width:640px; margin-bottom:52px; }
  .sec-head .kicker{
    display:inline-flex; align-items:center; gap:8px; font-size:13.5px; font-weight:600;
    padding:6px 14px; border-radius:999px; margin-bottom:18px;
  }
  .sec-head h2{ font-size:clamp(28px,3.4vw,40px); max-width:16ch; }
  .sec-head p{ margin-top:16px; font-size:16.5px; max-width:52ch; }

  .rider-section{ background:linear-gradient(180deg, var(--paper) 0%, var(--rider-tint) 130%); }
  .driver-section{ background:linear-gradient(180deg, var(--paper) 0%, var(--driver-tint) 130%); }

  .kicker.rider{ background:var(--rider-tint-2); color:var(--rider-deep); }
  .kicker.driver{ background:var(--driver-tint-2); color:var(--driver-deep); }

  .track-wrap{ position:relative; margin:0 -32px; }
  .track{
    display:flex; gap:30px; overflow-x:auto; padding:6px 32px 18px;
    scroll-snap-type:x proximity;
  }
  .track::-webkit-scrollbar{ height:0; }
  .track{ cursor:grab; }
  .track.dragging{ cursor:grabbing; scroll-snap-type:none; }
  .track > .phone-shell{ flex:0 0 auto; }
  .track-wrap::after{
    content:''; position:absolute; top:0; right:0; bottom:18px; width:90px;
    background:linear-gradient(to right, transparent, var(--paper));
    pointer-events:none;
  }
  .rider-section .track-wrap::after{ background:linear-gradient(to right, transparent, var(--rider-tint)); }
  .driver-section .track-wrap::after{ background:linear-gradient(to right, transparent, var(--driver-tint)); }
  .track > .phone-shell{ scroll-snap-align:start; }
  .phone.small{ --pw:196px; --ph:410px; border-radius:38px; }
  .phone.small .screen{ border-radius:30px; }
  .phone.small .dyn-island{ width:70px; height:20px; top:10px; }
  .phone.small .status-bar{ padding:11px 18px 0; font-size:11px; }
  .phone.small .home-ind{ width:96px; bottom:6px; }

  /* ---------- INTRO / HOW IT WORKS ---------- */
  .intro{ padding:88px 0 0; }
  .intro-grid{
    display:grid; grid-template-columns:repeat(3,1fr); gap:28px; margin-top:44px;
  }
  .intro-card{
    border:1px solid var(--line); border-radius:22px; padding:30px 26px;
    background:#fff;
  }
  .intro-card .ico{
    width:42px; height:42px; border-radius:12px; display:flex; align-items:center;
    justify-content:center; margin-bottom:20px;
  }
  .intro-card h3{ font-size:18px; margin-bottom:10px; }
  .intro-card p{ font-size:14.5px; }
  @media (max-width:820px){ .intro-grid{ grid-template-columns:1fr; } }

  /* ---------- MATCH SECTION ---------- */
  .match-section{ padding:110px 0 130px; position:relative; }
  .match-head{ text-align:center; max-width:560px; margin:0 auto 60px; }
  .match-head h2{ font-size:clamp(28px,3.4vw,40px); }
  .match-head p{ margin:16px auto 0; font-size:16.5px; }
  .match-row{
    display:flex; align-items:center; justify-content:center; gap:0; position:relative;
  }
  .match-row .phone-shell{ position:relative; z-index:2; }
  .match-link{
    width:min(220px, 16vw); height:2px; position:relative; margin:0 -6px; z-index:1;
    background:repeating-linear-gradient(to right, var(--line) 0 8px, transparent 8px 16px);
    top:-58px;
  }
  .match-link::before, .match-link::after{
    content:''; position:absolute; top:50%; width:9px; height:9px; border-radius:50%;
    transform:translateY(-50%);
  }
  .match-link::before{ left:-4px; background:var(--rider); }
  .match-link::after{ right:-4px; background:var(--driver); }
  .match-pulse{
    position:absolute; top:50%; left:0; width:9px; height:9px; border-radius:50%;
    background:var(--ink); transform:translateY(-50%);
    animation:travel 2.6s ease-in-out infinite;
  }
  @keyframes travel{
    0%{ left:0; opacity:0; } 8%{ opacity:1; } 92%{ opacity:1; } 100%{ left:100%; opacity:0; }
  }
  @media (max-width:760px){
    .match-row{ flex-direction:column; gap:64px; }
    .match-link{ width:2px; height:80px; top:0; transform:rotate(90deg); }
  }

  /* ---------- FOOTER ---------- */
  footer{ border-top:1px solid var(--line); padding:52px 0; }
  footer .wrap{ display:flex; justify-content:space-between; align-items:center; flex-wrap:wrap; gap:20px; }
  footer p{ font-size:13px; }
  .foot-links{ display:flex; gap:26px; }
  .foot-links a{ font-size:13px; color:var(--ink-soft); }
  .foot-links a:hover{ color:var(--ink); }

  @media (max-width:600px){
    .wrap{ padding:0 20px; }
    .phone{ --pw:210px; --ph:440px; }
    .phone.large{ --pw:230px; --ph:480px; }
  }

  /* ---------- SCROLL REVEAL ---------- */
  .reveal{
    opacity:0; transform:translateY(26px);
    transition:opacity .7s cubic-bezier(.2,.8,.2,1), transform .7s cubic-bezier(.2,.8,.2,1);
  }
  .reveal.in-view{ opacity:1; transform:translateY(0); }

  .reveal-stagger > *{
    opacity:0; transform:translateY(22px) scale(.97);
    transition:opacity .6s cubic-bezier(.2,.8,.2,1), transform .6s cubic-bezier(.2,.8,.2,1);
  }
  .reveal-stagger > *.in-view{ opacity:1; transform:translateY(0) scale(1); }
  .reveal-stagger.in-view > *{ opacity:1; transform:translateY(0) scale(1); }
  .reveal-stagger > *:nth-child(1){ transition-delay:.02s; }
  .reveal-stagger > *:nth-child(2){ transition-delay:.1s; }
  .reveal-stagger > *:nth-child(3){ transition-delay:.18s; }
  .reveal-stagger > *:nth-child(4){ transition-delay:.26s; }
  .reveal-stagger > *:nth-child(5){ transition-delay:.34s; }
  .reveal-stagger > *:nth-child(6){ transition-delay:.4s; }
  .reveal-stagger > *:nth-child(7){ transition-delay:.46s; }
  .reveal-stagger > *:nth-child(8){ transition-delay:.52s; }

  /* ---------- PHONE HOVER ---------- */
  .phone-shell{ perspective:900px; }
  .phone{ transition:transform .4s cubic-bezier(.2,.8,.2,1), box-shadow .4s ease; }
  .phone-shell:hover .phone{
    transform:rotateY(-7deg) rotateX(3deg) translateY(-6px);
    box-shadow:0 2px 0 rgba(255,255,255,.15) inset, 0 50px 80px -28px rgba(15,16,20,.6);
  }

  /* ---------- ANNOTATIONS ---------- */
  .annot-wrap{ position:relative; }
  .callout{
    position:absolute; display:flex; align-items:center; gap:8px; z-index:30;
    opacity:0; transition:opacity .6s ease .4s, transform .6s ease .4s;
  }
  .annot-wrap.in-view .callout{ opacity:1; }
  .callout-tag{
    background:#fff; border:1px solid var(--line); border-radius:9px;
    padding:6px 11px; font-size:11.5px; font-weight:600; color:var(--ink);
    white-space:nowrap; box-shadow:0 8px 18px -10px rgba(0,0,0,.3);
  }
  .callout .line{ width:28px; height:1.5px; background:var(--ink); opacity:.3; flex-shrink:0; }
  .callout .dotend{ width:5px; height:5px; border-radius:50%; background:var(--ink); opacity:.4; flex-shrink:0; }
  @media (max-width:1040px){ .callout{ display:none; } }

  /* ---------- CAROUSEL PROGRESS ---------- */
  .track-progress{
    height:3px; background:var(--line); border-radius:2px;
    margin:26px 0 8px; position:relative; overflow:hidden; max-width:100%;
  }
  .progress-fill{
    position:absolute; left:0; top:0; bottom:0; width:20%;
    border-radius:2px; transition:width .12s linear;
  }
  .track-hint{ font-size:12px; color:var(--ink-soft); margin-top:8px; }

  /* ---------- MATCH STORY ---------- */
  .matched-badge{
    position:absolute; left:50%; top:50%; transform:translate(-50%,-190%) scale(.9);
    background:var(--ink); color:#fff; font-size:11px; font-weight:600;
    padding:6px 12px; border-radius:999px; opacity:0; white-space:nowrap;
    display:flex; align-items:center; gap:6px; z-index:5;
    animation:matchedFade 2.6s ease-in-out infinite;
  }
  @keyframes matchedFade{
    0%,78%{ opacity:0; transform:translate(-50%,-190%) scale(.9); }
    88%,95%{ opacity:1; transform:translate(-50%,-190%) scale(1); }
    100%{ opacity:0; transform:translate(-50%,-190%) scale(.95); }
  }
  .match-row .btn-pair > div:last-child{ animation:acceptGlow 2.6s ease-in-out infinite; }
  @keyframes acceptGlow{
    0%,84%{ box-shadow:none; }
    90%,96%{ box-shadow:0 0 0 6px rgba(11,110,79,.28); }
    100%{ box-shadow:none; }
  }
  .match-step-tag{
    font-size:11.5px; font-weight:600; color:var(--ink-soft);
    text-align:center; margin-bottom:14px;
  }

  /* ---------- TRIP STATE COMPONENTS ---------- */
  .radar-ring{
    position:absolute; left:50%; top:50%; width:14px; height:14px; border-radius:50%;
    border:2px solid var(--rider); transform:translate(-50%,-50%); opacity:0;
    animation:radarPulse 2.6s ease-out infinite;
  }
  .radar-ring:nth-child(2){ animation-delay:.8s; }
  .radar-ring:nth-child(3){ animation-delay:1.6s; }
  @keyframes radarPulse{
    0%{ width:14px; height:14px; opacity:.6; }
    100%{ width:160px; height:160px; opacity:0; }
  }
  .driver-card{ display:flex; align-items:center; gap:10px; margin-bottom:12px; }
  .avatar-circle{
    width:40px; height:40px; border-radius:50%; background:var(--rider-tint-2);
    display:flex; align-items:center; justify-content:center; font-weight:700;
    color:var(--rider-deep); font-size:14px; flex-shrink:0;
  }
  .driver-meta .name{ font-size:12.5px; font-weight:700; }
  .driver-meta .sub{ font-size:10.5px; color:var(--ink-soft); margin-top:1px; }
  .icon-btn-row{ display:flex; gap:8px; margin-left:auto; }
  .icon-btn{
    width:34px; height:34px; border-radius:50%; border:1.5px solid var(--line);
    display:flex; align-items:center; justify-content:center; flex-shrink:0;
  }
  .pin-badge{
    font-size:10.5px; font-weight:600; color:var(--ink-soft);
    display:flex; justify-content:space-between; align-items:center;
    padding:8px 10px; background:var(--paper-dim); border-radius:10px; margin-bottom:12px;
  }
  .pin-badge b{ color:var(--ink); letter-spacing:.06em; }
  .stars-row{ display:flex; gap:7px; justify-content:center; margin:16px 0 14px; }
  .tip-chips{ display:flex; gap:6px; margin-bottom:14px; }
  .tip-chip{
    flex:1; height:34px; border-radius:999px; border:1.5px solid var(--line);
    display:flex; align-items:center; justify-content:center; font-size:11px; font-weight:600;
  }
  .tip-chip.active{ background:var(--rider); border-color:var(--rider); color:#fff; }

  /* ---------- ACCOUNT LAYER (DRAWER / WALLET / SETTINGS) ---------- */
  .drawer-scrim{ position:absolute; inset:0; background:rgba(10,10,12,.4); }
  .drawer-panel{
    position:absolute; top:0; bottom:0; left:0; width:76%; background:#fff;
    padding:16px 16px 0; box-shadow:10px 0 26px -14px rgba(0,0,0,.4); z-index:8;
  }
  .drawer-profile{
    display:flex; align-items:center; gap:10px; margin:8px 0 14px;
    padding-bottom:14px; border-bottom:1px solid var(--line);
  }
  .drawer-profile .name{ font-size:12.5px; font-weight:700; }
  .drawer-profile .sub{ font-size:10px; color:var(--ink-soft); margin-top:1px; }
  .menu-row{ display:flex; align-items:center; gap:10px; padding:9px 2px; font-size:11.5px; font-weight:600; color:var(--ink); }

  .acct-pane{ padding:20px 18px 0; height:100%; display:flex; flex-direction:column; }
  .acct-pane h4{ font-family:'Space Grotesk'; font-size:16px; margin-bottom:14px; }

  .wallet-card{
    border-radius:16px; padding:16px; color:#fff; margin-bottom:14px;
    background:linear-gradient(135deg, var(--rider), var(--rider-deep));
  }
  .wallet-card .label{ font-size:10px; opacity:.8; margin-bottom:5px; }
  .wallet-card .amount{ font-family:'Space Grotesk'; font-size:21px; font-weight:700; }
  .pay-row{ display:flex; align-items:center; gap:10px; padding:11px 2px; border-top:1px solid var(--paper-dim); font-size:11.5px; }
  .pay-row:first-of-type{ border-top:none; }
  .pay-icon{
    width:30px; height:20px; border-radius:5px; background:var(--paper-dim);
    display:flex; align-items:center; justify-content:center; font-size:8.5px;
    font-weight:700; color:var(--ink-soft); flex-shrink:0;
  }
  .pay-row .tag{ margin-left:auto; font-size:9px; color:var(--ink-soft); }

  .settings-group-label{ font-size:10px; font-weight:700; color:var(--ink-soft); letter-spacing:.03em; margin:12px 0 4px; }
  .settings-row{ display:flex; align-items:center; gap:10px; padding:8px 2px; font-size:11.5px; }
  .settings-row .t{ flex:1; font-weight:600; }
  .settings-row .d{ font-size:9.5px; font-weight:400; color:var(--ink-soft); margin-top:1px; }
  .toggle{ width:32px; height:19px; border-radius:999px; background:var(--line); position:relative; flex-shrink:0; }
  .toggle.on{ background:var(--rider); }
  .toggle::after{
    content:''; position:absolute; top:2px; left:2px; width:15px; height:15px;
    border-radius:50%; background:#fff; transition:transform .2s ease;
  }
  .toggle.on::after{ transform:translateX(13px); }

  @media (prefers-reduced-motion: reduce){
    *{ animation-duration:0.01ms !important; animation-iteration-count:1 !important; }
    .reveal, .reveal-stagger > *{ opacity:1 !important; transform:none !important; }
  }
</style>
</head>
<body>

<nav>
  <div class="wrap">
    <div class="logo">vaya<span class="dot"></span></div>
    <div class="nav-links">
      <a href="#riders">Riders</a>
      <a href="#drivers">Drivers</a>
      <a href="#account">Account &amp; safety</a>
      <a href="#how">How it works</a>
    </div>
    <a class="nav-cta" href="#riders">Get the app</a>
  </div>
</nav>

<!-- ================= HERO ================= -->
<header class="hero">
  <div class="wrap">
    <div class="hero-copy">
      <h1>Getting around Pietermaritzburg, sorted.</h1>
      <p class="lead">Vaya puts riders and drivers on the same map. Request a ride in a few taps, or switch on and start earning on your own schedule.</p>
      <div class="hero-ctas">
        <a class="btn btn-rider" href="#riders">Request a ride</a>
        <a class="btn btn-driver" href="#drivers">Start driving</a>
      </div>
      <p class="hero-note"><b>R48</b> average trip across town &nbsp;·&nbsp; drivers keep <b>85%</b> of every fare</p>
    </div>

    <div class="hero-phone-wrap">
      <div class="annot-wrap">
      <div class="callout" style="top:30%; left:-6px; transform:translateX(-100%);">
        <div class="callout-tag">Live pickup &amp; drop-off pins</div>
        <div class="line"></div><div class="dotend"></div>
      </div>
      <div class="callout" style="top:72%; right:-6px; transform:translateX(100%); flex-direction:row-reverse;">
        <div class="dotend"></div><div class="line"></div>
        <div class="callout-tag">Fare locked before you ride</div>
      </div>
      <div class="phone-shell">
        <div class="phone large">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-rider">
              <svg class="road" width="100%" height="100%" viewBox="0 0 288 600" fill="none">
                <path d="M0 210 C 90 190, 120 260, 210 240 S 320 300, 288 340" stroke="#ffffff" stroke-width="10" stroke-linecap="round"/>
                <path d="M40 600 C 60 480, 10 420, 90 360" stroke="#ffffff" stroke-width="8" stroke-linecap="round"/>
              </svg>
              <div class="pin" style="left:32%; top:38%; background:var(--rider);"></div>
              <div class="pin you" style="left:64%; top:57%; background:var(--rider-deep);"></div>
            </div>
            <div class="status-bar">
              <span>09:41</span>
              <div class="status-icons">
                <svg width="16" height="11" viewBox="0 0 18 12" fill="currentColor"><rect x="0" y="7" width="3" height="5" rx="0.5"/><rect x="5" y="5" width="3" height="7" rx="0.5"/><rect x="10" y="3" width="3" height="9" rx="0.5"/><rect x="15" y="0" width="3" height="12" rx="0.5"/></svg>
                <svg width="14" height="11" viewBox="0 0 16 12" fill="none" stroke="currentColor" stroke-width="1.5"><path d="M1 4.5a10 10 0 0 1 14 0"/><path d="M3.5 7a6.5 6.5 0 0 1 9 0"/><circle cx="8" cy="10" r="1" fill="currentColor" stroke="none"/></svg>
                <svg width="23" height="11" viewBox="0 0 25 12" fill="none"><rect x="0.5" y="0.5" width="21" height="11" rx="3" stroke="currentColor" opacity="0.4"/><rect x="2" y="2" width="18" height="8" rx="1.5" fill="currentColor"/><rect x="22.5" y="4" width="1.5" height="4" rx="0.7" fill="currentColor" opacity="0.4"/></svg>
              </div>
            </div>
            <div class="sheet" style="border-radius:26px 26px 0 0;">
              <div class="grabber"></div>
              <div class="label-sm">Pickup &rarr; UKZN Pietermaritzburg</div>
              <div class="row"><span class="l">Estimated fare</span><span class="r">R48</span></div>
              <div class="cta-full" style="background:var(--rider); animation:softPulse 2.4s ease-in-out infinite;">Request ride</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
      </div>
      </div>
    </div>
  </div>
</header>

<style>
  @keyframes softPulse{
    0%,100%{ box-shadow:0 0 0 0 rgba(75,58,224,.35); }
    50%{ box-shadow:0 0 0 10px rgba(75,58,224,0); }
  }
</style>

<!-- ================= HOW IT WORKS ================= -->
<section class="intro" id="how">
  <div class="wrap">
    <div class="sec-head reveal" style="margin-bottom:0;">
      <h2>One platform. Two very different days.</h2>
      <p>A rider just wants to get somewhere without thinking about it. A driver wants a full, predictable day of fares. Vaya is built as two separate, focused apps that happen to share one matching engine underneath — scroll down to see exactly how each one works, screen by screen.</p>
    </div>
    <div class="intro-grid reveal-stagger">
      <div class="intro-card">
        <div class="ico" style="background:var(--rider-tint);">
          <svg width="20" height="20" viewBox="0 0 20 20" fill="none" stroke="var(--rider)" stroke-width="1.6"><circle cx="10" cy="8" r="3.2"/><path d="M4 17c1-3.4 3.6-5 6-5s5 1.6 6 5"/></svg>
        </div>
        <h3>Built for riders</h3>
        <p>Search a destination, see the fare before you confirm, and watch your driver get closer on the map — nothing to negotiate, no surprises at drop-off.</p>
      </div>
      <div class="intro-card">
        <div class="ico" style="background:var(--driver-tint);">
          <svg width="20" height="20" viewBox="0 0 20 20" fill="none" stroke="var(--driver)" stroke-width="1.6"><rect x="3" y="8" width="14" height="6" rx="2"/><circle cx="6.5" cy="15.5" r="1.4"/><circle cx="13.5" cy="15.5" r="1.4"/><path d="M5 8l1.5-3.5h7L15 8"/></svg>
        </div>
        <h3>Built for drivers</h3>
        <p>Go online when it suits you, offline when it doesn't. Every request shows the fare and distance before you accept, so declining never costs you anything.</p>
      </div>
      <div class="intro-card">
        <div class="ico" style="background:var(--paper-dim);">
          <svg width="20" height="20" viewBox="0 0 20 20" fill="none" stroke="var(--ink)" stroke-width="1.6"><circle cx="10" cy="10" r="7"/><path d="M10 6v4l3 2"/></svg>
        </div>
        <h3>Matched in seconds</h3>
        <p>The moment a ride is requested, every nearby online driver is notified at once — first to accept gets the fare, most trips are matched in under a minute.</p>
      </div>
    </div>
  </div>
</section>

<!-- ================= RIDER SHOWCASE ================= -->
<section class="showcase rider-section" id="riders">
  <div class="wrap">
    <div class="sec-head reveal">
      <span class="kicker rider">For riders</span>
      <h2>From request to receipt</h2>
      <p>The rider flow doesn't stop at "Request ride" — it follows the trip all the way through matching, the ride itself, and rating the driver at the end.</p>
    </div>
    <div class="track-progress"><div class="progress-fill" style="background:var(--rider);"></div></div>
    <p class="track-hint">Scroll the phones to follow the full trip &rarr;</p>
  </div>

  <div class="track-wrap">
    <div class="track reveal-stagger">

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="s-login">
              <div class="status-bar" style="padding:0;"><span>09:41</span><div class="status-icons"><svg width="14" height="10" viewBox="0 0 18 12" fill="currentColor"><rect x="0" y="7" width="3" height="5"/><rect x="5" y="5" width="3" height="7"/><rect x="10" y="3" width="3" height="9"/><rect x="15" y="0" width="3" height="12"/></svg></div></div>
              <div class="center">
                <div class="mark" style="background:var(--rider);"></div>
                <h4>Get moving</h4>
                <div class="sub">Enter your number to continue</div>
              </div>
              <div class="field">+27 71 234 5678</div>
              <div class="cta-full" style="background:var(--rider);">Continue</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--rider);">1</span>Log in</p>
        <p class="step-cap">One-time verification, no password to remember or reset.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-rider">
              <svg class="road" width="100%" height="100%" viewBox="0 0 196 410" fill="none">
                <path d="M0 150 C 60 140, 90 190, 150 170 S 220 210, 196 250" stroke="#fff" stroke-width="7" stroke-linecap="round"/>
              </svg>
              <div class="pin you" style="left:48%; top:50%; background:var(--rider);"></div>
            </div>
            <div class="search-pill">
              <svg width="13" height="13" viewBox="0 0 20 20" fill="none" stroke="currentColor" stroke-width="1.8"><circle cx="9" cy="9" r="6"/><path d="M17 17l-3.5-3.5"/></svg>
              Where to?
            </div>
            <div class="avatar-btn">
              <svg width="14" height="14" viewBox="0 0 20 20" fill="none" stroke="var(--ink)" stroke-width="1.6"><path d="M4 5h12M4 10h12M4 15h12"/></svg>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--rider);">2</span>Home loads</p>
        <p class="step-cap">Map centers on you and starts listening for nearby drivers.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="search-page">
              <div class="status-bar" style="padding:0 0 12px;"><span>09:41</span><span></span></div>
              <div class="head">
                <svg width="15" height="15" viewBox="0 0 20 20" fill="none" stroke="var(--ink)" stroke-width="1.8"><path d="M12 4l-6 6 6 6"/></svg>
                <div class="input">Search&hellip;</div>
              </div>
              <h5>Recent</h5>
              <div class="result-row"><svg class="ico-dot" viewBox="0 0 20 20" fill="none" stroke="currentColor" stroke-width="1.6"><circle cx="10" cy="10" r="7"/><path d="M10 6v4l3 2"/></svg>UKZN Pietermaritzburg</div>
              <div class="result-row"><svg class="ico-dot" viewBox="0 0 20 20" fill="none" stroke="currentColor" stroke-width="1.6"><circle cx="10" cy="10" r="7"/><path d="M10 6v4l3 2"/></svg>Liberty Midlands Mall</div>
              <div class="result-row"><svg class="ico-dot" viewBox="0 0 20 20" fill="none" stroke="currentColor" stroke-width="1.6"><circle cx="10" cy="10" r="7"/><path d="M10 6v4l3 2"/></svg>Town Hill</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--rider);">3</span>Search expands</p>
        <p class="step-cap">Recent and saved spots surface first — most trips need zero typing.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="search-page">
              <div class="status-bar" style="padding:0 0 12px;"><span>09:41</span><span></span></div>
              <div class="head">
                <svg width="15" height="15" viewBox="0 0 20 20" fill="none" stroke="var(--ink)" stroke-width="1.8"><path d="M12 4l-6 6 6 6"/></svg>
                <div class="input">UKZN</div>
              </div>
              <div class="result-row active"><svg class="ico-dot" viewBox="0 0 20 20" fill="none" stroke="var(--rider)" stroke-width="1.8"><circle cx="10" cy="10" r="7"/><path d="M10 6v4l3 2"/></svg>UKZN Pietermaritzburg</div>
              <div class="result-row"><svg class="ico-dot" viewBox="0 0 20 20" fill="none" stroke="currentColor" stroke-width="1.6"><circle cx="10" cy="10" r="7"/><path d="M10 6v4l3 2"/></svg>UKZN Howard College</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--rider);">4</span>Choose destination</p>
        <p class="step-cap">Tapping a result locks in exactly where you're headed.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-rider">
              <div class="pin" style="left:30%; top:40%; background:var(--rider);"></div>
              <div class="pin" style="left:66%; top:60%; background:var(--rider-deep);"></div>
            </div>
            <div class="sheet">
              <div class="grabber"></div>
              <div class="label-sm">Pickup &rarr; UKZN Pietermaritzburg</div>
              <div class="row"><span class="l">Est. fare</span><span class="r">R48</span></div>
              <div class="cta-full" style="background:var(--rider);">Request ride</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--rider);">5</span>Request ride</p>
        <p class="step-cap">Fare's confirmed up front — no meter, no surprise at drop-off.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-rider">
              <div class="radar-ring"></div>
              <div class="radar-ring"></div>
              <div class="radar-ring"></div>
              <div class="pin you" style="left:50%; top:50%; background:var(--rider);"></div>
            </div>
            <div class="sheet">
              <div class="grabber"></div>
              <div class="label-sm">Finding your driver&hellip;</div>
              <div class="row" style="margin-bottom:14px;"><span class="l">Usually under a minute</span></div>
              <div class="cta-full" style="background:#fff; color:var(--ink); border:1.5px solid var(--line);">Cancel request</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--rider);">6</span>Finding a driver</p>
        <p class="step-cap">Nearby online drivers are pinged at once — you can still cancel free.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-rider">
              <svg class="road" width="100%" height="100%" viewBox="0 0 196 410" fill="none">
                <path d="M20 320 C 70 260, 60 190, 130 150" stroke="#fff" stroke-width="7" stroke-linecap="round"/>
              </svg>
              <div class="pin" style="left:60%; top:34%; background:var(--rider-deep);"></div>
            </div>
            <div class="sheet">
              <div class="grabber"></div>
              <div class="pin-badge"><span>Share PIN with driver</span><b>4821</b></div>
              <div class="driver-card">
                <div class="avatar-circle">SM</div>
                <div class="driver-meta">
                  <div class="name">Sipho M. &middot; 4.9 &#9733;</div>
                  <div class="sub">Toyota Corolla &middot; CA 123-456</div>
                </div>
                <div class="icon-btn-row">
                  <div class="icon-btn"><svg width="13" height="13" viewBox="0 0 20 20" fill="none" stroke="var(--ink)" stroke-width="1.7"><path d="M4 4l3 8-3 8 16-8z"/></svg></div>
                  <div class="icon-btn"><svg width="13" height="13" viewBox="0 0 20 20" fill="none" stroke="var(--ink)" stroke-width="1.7"><path d="M4 5h12v9H8l-4 3V5z"/></svg></div>
                </div>
              </div>
              <div class="cta-full" style="background:var(--rider);">Driver arriving &middot; 3 min</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--rider);">7</span>Driver assigned</p>
        <p class="step-cap">Plate, PIN and live ETA — the same details you'd check in person.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="s-login" style="padding-top:26px;">
              <div class="status-bar" style="padding:0;"><span>09:41</span><span></span></div>
              <div class="center" style="justify-content:flex-start; padding-top:18px;">
                <div class="mark" style="background:var(--rider); width:30px; height:30px;"></div>
                <h4 style="margin-top:12px;">Trip complete</h4>
                <div class="sub">UKZN Pietermaritzburg &middot; R48</div>
              </div>
              <div class="stars-row">
                <svg viewBox="0 0 20 20" fill="var(--rider)"><path d="M10 1l2.6 5.9 6.4.6-4.8 4.3 1.4 6.2L10 14.9 4.4 18l1.4-6.2L1 7.5l6.4-.6z"/></svg>
                <svg viewBox="0 0 20 20" fill="var(--rider)"><path d="M10 1l2.6 5.9 6.4.6-4.8 4.3 1.4 6.2L10 14.9 4.4 18l1.4-6.2L1 7.5l6.4-.6z"/></svg>
                <svg viewBox="0 0 20 20" fill="var(--rider)"><path d="M10 1l2.6 5.9 6.4.6-4.8 4.3 1.4 6.2L10 14.9 4.4 18l1.4-6.2L1 7.5l6.4-.6z"/></svg>
                <svg viewBox="0 0 20 20" fill="var(--rider)"><path d="M10 1l2.6 5.9 6.4.6-4.8 4.3 1.4 6.2L10 14.9 4.4 18l1.4-6.2L1 7.5l6.4-.6z"/></svg>
                <svg viewBox="0 0 20 20" fill="var(--line)"><path d="M10 1l2.6 5.9 6.4.6-4.8 4.3 1.4 6.2L10 14.9 4.4 18l1.4-6.2L1 7.5l6.4-.6z"/></svg>
              </div>
              <div class="tip-chips">
                <div class="tip-chip">R5</div>
                <div class="tip-chip active">R10</div>
                <div class="tip-chip">R15</div>
              </div>
              <div class="cta-full" style="background:var(--rider);">Submit rating</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--rider);">8</span>Rate &amp; tip</p>
        <p class="step-cap">A fare breakdown and quick rating close out every trip.</p>
      </div>

    </div>
  </div>
</section>

<!-- ================= DRIVER SHOWCASE ================= -->
<section class="showcase driver-section" id="drivers">
  <div class="wrap">
    <div class="sec-head reveal">
      <span class="kicker driver">For drivers</span>
      <h2>Go online, start earning</h2>
      <p>One toggle takes a driver from offline to available. From there, every incoming request shows the fare and distance before they commit — accepting is always a choice, never automatic.</p>
    </div>
    <div class="track-progress"><div class="progress-fill" style="background:var(--driver);"></div></div>
    <p class="track-hint">Scroll the phones to follow the full flow &rarr;</p>
  </div>

  <div class="track-wrap">
    <div class="track reveal-stagger">

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="s-login">
              <div class="status-bar" style="padding:0;"><span>09:41</span><span></span></div>
              <div class="center">
                <div class="mark" style="background:var(--driver);"></div>
                <h4>Ready to drive?</h4>
                <div class="sub">Enter your number to continue</div>
              </div>
              <div class="field">+27 71 234 5678</div>
              <div class="cta-full" style="background:var(--driver);">Continue</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--driver);">1</span>Log in</p>
        <p class="step-cap">Same one-time verification as riders — no separate setup.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-driver">
              <svg class="road" width="100%" height="100%" viewBox="0 0 196 410" fill="none">
                <path d="M10 120 C 70 110, 100 170, 170 150" stroke="#fff" stroke-width="7" stroke-linecap="round"/>
              </svg>
            </div>
            <div class="top-pill">Go online</div>
            <div class="sheet">
              <div class="grabber"></div>
              <div class="label-sm">Today's earnings</div>
              <div style="font-family:'Space Grotesk'; font-size:20px; font-weight:700; margin-bottom:12px;">R0.00</div>
              <div class="cta-full" style="background:var(--driver);">Go online</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--driver);">2</span>Home &middot; offline</p>
        <p class="step-cap">Nothing happens until you go online. Earnings reset each day.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-driver"></div>
            <div class="top-pill dark"><span class="dot-live"></span>Going online&hellip;</div>
            <div class="home-ind on-dark"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--driver);">3</span>Toggling on</p>
        <p class="step-cap">A short animation confirms you're now visible to nearby riders.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-driver">
              <div class="pin you" style="left:50%; top:44%; background:var(--driver);"></div>
            </div>
            <div class="top-pill dark"><span class="dot-live"></span>You're online</div>
            <div class="sheet">
              <div class="grabber"></div>
              <div class="row" style="margin-bottom:2px;"><span class="l">Looking for rides&hellip;</span></div>
              <div class="label-sm" style="margin-bottom:12px;">Stay in a busy area</div>
              <div class="cta-full" style="background:#fff; color:var(--ink); border:1.5px solid var(--line);">Go offline</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--driver);">4</span>Waiting</p>
        <p class="step-cap">You can still browse the map and reposition while requests come in.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-driver">
              <div class="pin you" style="left:44%; top:40%; background:var(--driver);"></div>
            </div>
            <div class="sheet">
              <div class="grabber"></div>
              <div class="row"><span class="l">New ride request</span><span class="r">R48</span></div>
              <div class="label-sm" style="margin-bottom:14px;">1.2 km away &middot; 3 min</div>
              <div class="btn-pair">
                <div style="background:#fff; border:1.5px solid var(--line); color:var(--ink);">Decline</div>
                <div style="background:var(--driver); color:#fff;">Accept</div>
              </div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--driver);">5</span>Accept or decline</p>
        <p class="step-cap">Fare and distance shown before you commit — declining costs nothing.</p>
      </div>

    </div>
  </div>
</section>

<!-- ================= MATCH SECTION ================= -->
<section class="match-section">
  <div class="wrap">
    <div class="match-head reveal">
      <span class="kicker" style="background:var(--paper-dim); color:var(--ink);">Under the hood</span>
      <h2>Every ride starts the same way</h2>
      <p>A rider requests, the request reaches every nearby online driver at once, and the first to accept gets the fare. Watch the two screens below — they're the same moment, seen from both sides.</p>
    </div>

    <div class="match-row reveal-stagger">
      <div>
      <p class="match-step-tag">Step 1 &middot; 09:41:03</p>
      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-rider">
              <div class="pin" style="left:32%; top:38%; background:var(--rider);"></div>
              <div class="pin" style="left:66%; top:60%; background:var(--rider-deep);"></div>
            </div>
            <div class="sheet">
              <div class="grabber"></div>
              <div class="label-sm">Pickup &rarr; UKZN Pietermaritzburg</div>
              <div class="row"><span class="l">Est. fare</span><span class="r">R48</span></div>
              <div class="cta-full" style="background:var(--rider);">Request ride</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label" style="justify-content:center;"><span class="num" style="background:var(--rider);">R</span>Rider requests</p>
      </div>
      </div>

      <div class="match-link">
        <div class="match-pulse"></div>
        <div class="matched-badge">
          <svg width="10" height="10" viewBox="0 0 20 20" fill="none" stroke="#fff" stroke-width="2.4"><path d="M4 10l4 4 8-8"/></svg>
          Matched
        </div>
      </div>

      <div>
      <p class="match-step-tag">Step 2 &middot; 09:41:04</p>
      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-driver">
              <div class="pin you" style="left:44%; top:40%; background:var(--driver);"></div>
            </div>
            <div class="sheet">
              <div class="grabber"></div>
              <div class="row"><span class="l">New ride request</span><span class="r">R48</span></div>
              <div class="label-sm" style="margin-bottom:14px;">1.2 km away &middot; 3 min</div>
              <div class="btn-pair">
                <div style="background:#fff; border:1.5px solid var(--line); color:var(--ink);">Decline</div>
                <div style="background:var(--driver); color:#fff;">Accept</div>
              </div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label" style="justify-content:center;"><span class="num" style="background:var(--driver);">D</span>Driver accepts</p>
      </div>
      </div>
    </div>
  </div>
</section>

<!-- ================= ACCOUNT & SAFETY SHOWCASE ================= -->
<section class="showcase" id="account" style="background:linear-gradient(180deg, var(--paper) 0%, var(--paper-dim) 130%);">
  <div class="wrap">
    <div class="sec-head reveal">
      <span class="kicker" style="background:#fff; color:var(--ink); border:1px solid var(--line);">Account &amp; safety</span>
      <h2>More than the ride itself</h2>
      <p>Riders and drivers share one account layer underneath — trip history, saved payment methods, and safety settings all live behind a single drawer.</p>
    </div>
    <p class="track-hint">Scroll to see what's behind the menu &rarr;</p>
  </div>

  <div class="track-wrap">
    <div class="track reveal-stagger">

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="map map-rider" style="opacity:.5;"></div>
            <div class="drawer-scrim"></div>
            <div class="drawer-panel">
              <div class="status-bar" style="padding:0;"><span>09:41</span><span></span></div>
              <div class="drawer-profile">
                <div class="avatar-circle">TN</div>
                <div><div class="name">Thandi N.</div><div class="sub">+27 71 234 5678</div></div>
              </div>
              <div class="menu-row">
                <svg width="15" height="15" viewBox="0 0 20 20" fill="none" stroke="var(--ink)" stroke-width="1.6" opacity=".7"><circle cx="10" cy="10" r="7"/><path d="M10 6v4l3 2"/></svg>
                Ride history
              </div>
              <div class="menu-row">
                <svg width="15" height="15" viewBox="0 0 20 20" fill="none" stroke="var(--ink)" stroke-width="1.6" opacity=".7"><rect x="2" y="5" width="16" height="11" rx="2"/><path d="M2 8h16"/><circle cx="14" cy="12" r="1" fill="var(--ink)" stroke="none"/></svg>
                Wallet
              </div>
              <div class="menu-row">
                <svg width="15" height="15" viewBox="0 0 20 20" fill="none" stroke="var(--ink)" stroke-width="1.6" opacity=".7"><rect x="3" y="8" width="14" height="9" rx="1.5"/><path d="M3 8h14M10 8v9"/></svg>
                Promotions &amp; referrals
              </div>
              <div class="menu-row">
                <svg width="15" height="15" viewBox="0 0 20 20" fill="none" stroke="var(--ink)" stroke-width="1.6" opacity=".7"><circle cx="10" cy="10" r="7"/><path d="M9 8.3a1.6 1.6 0 1 1 2.3 1.4c-.6.3-.8.7-.8 1.3"/><circle cx="10" cy="13" r=".15" fill="var(--ink)" stroke="none"/></svg>
                Help &amp; support
              </div>
              <div class="menu-row" style="color:var(--rider);">
                <svg width="15" height="15" viewBox="0 0 20 20" fill="none" stroke="var(--rider)" stroke-width="1.6"><circle cx="10" cy="10" r="3"/><path d="M10 2v2M10 16v2M2 10h2M16 10h2M4.5 4.5l1.4 1.4M14.1 14.1l1.4 1.4M4.5 15.5l1.4-1.4M14.1 5.9l1.4-1.4"/></svg>
                Settings
              </div>
            </div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--ink);">A</span>Menu &amp; account</p>
        <p class="step-cap">One drawer, both apps — trip history, wallet, promos, and support.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="acct-pane">
              <div class="status-bar" style="padding:0 0 16px;"><span>09:41</span><span></span></div>
              <h4>Wallet</h4>
              <div class="wallet-card">
                <div class="label">In-app balance</div>
                <div class="amount">R120.00</div>
              </div>
              <div class="pay-row"><div class="pay-icon">CASH</div>Cash<span class="tag">Default</span></div>
              <div class="pay-row"><div class="pay-icon">VISA</div>Visa &middot;&middot;&middot;&middot; 4417</div>
              <div class="pay-row" style="color:var(--rider); font-weight:600;">+ Add payment method</div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--ink);">B</span>Wallet</p>
        <p class="step-cap">Cash, cards and in-app balance, with one method set as default.</p>
      </div>

      <div class="phone-shell">
        <div class="phone small">
          <div class="screen">
            <div class="dyn-island"></div>
            <div class="acct-pane">
              <div class="status-bar" style="padding:0 0 16px;"><span>09:41</span><span></span></div>
              <h4>Settings</h4>
              <div class="settings-group-label">Safety</div>
              <div class="settings-row"><div class="t">Share trip status<div class="d">Send live location to an emergency contact</div></div><div class="toggle on"></div></div>
              <div class="settings-row"><div class="t">PIN before trip starts<div class="d">Driver enters your PIN to begin the ride</div></div><div class="toggle on"></div></div>
              <div class="settings-row"><div class="t">SOS quick-dial<div class="d">Hold the power button to alert your contacts</div></div><div class="toggle"></div></div>
              <div class="settings-group-label">Preferences</div>
              <div class="settings-row"><div class="t">Distance in kilometres</div><div class="toggle on"></div></div>
            </div>
            <div class="home-ind"></div>
          </div>
        </div>
        <p class="step-label"><span class="num" style="background:var(--ink);">C</span>Safety settings</p>
        <p class="step-cap">PIN confirmation and SOS shortcuts, on by default.</p>
      </div>

    </div>
  </div>
</section>

<!-- ================= FOOTER ================= -->
<footer>
  <div class="wrap">
    <div class="logo" style="font-size:18px;">vaya<span class="dot"></span></div>
    <p>Currently matching rides across Pietermaritzburg, South Africa.</p>
    <div class="foot-links">
      <a href="#riders">Riders</a>
      <a href="#drivers">Drivers</a>
      <a href="#how">How it works</a>
    </div>
  </div>
</footer>

<script>
  document.addEventListener('DOMContentLoaded', () => {
    const io = new IntersectionObserver((entries) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          entry.target.classList.add('in-view');
          io.unobserve(entry.target);
        }
      });
    }, { threshold: 0.2 });

    document.querySelectorAll('.reveal, .reveal-stagger, .annot-wrap')
      .forEach(el => io.observe(el));

    document.querySelectorAll('.track').forEach(track => {
      const bar = track.closest('section').querySelector('.progress-fill');
      const update = () => {
        if (!bar) return;
        const max = track.scrollWidth - track.clientWidth;
        const pct = max > 0 ? track.scrollLeft / max : 0;
        bar.style.width = (20 + pct * 80) + '%';
      };
      track.addEventListener('scroll', update, { passive: true });
      update();

      // let a plain vertical mouse wheel scroll the row horizontally
      track.addEventListener('wheel', (e) => {
        if (Math.abs(e.deltaY) > Math.abs(e.deltaX)) {
          track.scrollLeft += e.deltaY;
          e.preventDefault();
        }
      }, { passive: false });

      // click-and-drag for mouse users (touch already scrolls natively)
      let isDown = false, startX = 0, startScroll = 0;
      track.addEventListener('mousedown', (e) => {
        isDown = true;
        track.classList.add('dragging');
        startX = e.pageX;
        startScroll = track.scrollLeft;
      });
      window.addEventListener('mouseup', () => { isDown = false; track.classList.remove('dragging'); });
      window.addEventListener('mousemove', (e) => {
        if (!isDown) return;
        track.scrollLeft = startScroll - (e.pageX - startX);
      });
    });
  });
</script>
</body>
</html>
