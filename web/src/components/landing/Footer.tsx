import React from 'react';
import { ElevoraLogo } from './ElevoraLogo';

export const Footer: React.FC = () => {
  return (
    <footer className="bg-[#050810] border-t border-slate-800 text-slate-400 text-xs">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pt-16 pb-12">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-5 gap-10 mb-12">
          
          {/* Brand Col with correct Elevora 3-bar logo */}
          <div className="lg:col-span-2 space-y-4">
            <ElevoraLogo size="md" showSubtitle={false} />
            <p className="text-slate-400 text-xs max-w-sm leading-relaxed">
              Professional English Learning Platform empowering leaders and teams to communicate with poise, precision, and persuasive executive presence.
            </p>
          </div>

          {/* Quick Links */}
          <div className="space-y-3">
            <h4 className="text-sm font-bold text-white uppercase tracking-wider">Quick Links</h4>
            <ul className="space-y-2">
              <li><a href="#hero" className="hover:text-amber-400 transition-colors">Home</a></li>
              <li><a href="#courses" className="hover:text-amber-400 transition-colors">Courses & Curriculum</a></li>
              <li><a href="#features" className="hover:text-amber-400 transition-colors">AI Speaking Tutor</a></li>
              <li><a href="#pricing" className="hover:text-amber-400 transition-colors">Pricing & Plans</a></li>
              <li><a href="#testimonials" className="hover:text-amber-400 transition-colors">Learner Reviews</a></li>
            </ul>
          </div>

          {/* Resources */}
          <div className="space-y-3">
            <h4 className="text-sm font-bold text-white uppercase tracking-wider">Resources</h4>
            <ul className="space-y-2">
              <li><a href="#blog" className="hover:text-amber-400 transition-colors">Blog & Insights</a></li>
              <li><a href="#courses" className="hover:text-amber-400 transition-colors">Learning Tips</a></li>
              <li><a href="#pricing" className="hover:text-amber-400 transition-colors">FAQ</a></li>
              <li><a href="mailto:support@elevora.sriddha.com" className="hover:text-amber-400 transition-colors">Contact Support</a></li>
            </ul>
          </div>

          {/* Get The App */}
          <div className="space-y-3">
            <h4 className="text-sm font-bold text-white uppercase tracking-wider">Get the App</h4>
            <p className="text-[11px] text-slate-400">Practice anywhere on Android & Web</p>
            <div className="space-y-2 pt-1">
              {/* Google Play Store Badge Mockup */}
              <div className="flex items-center gap-2.5 px-3 py-2 rounded-xl bg-slate-900 border border-slate-800 hover:border-amber-500/40 transition-colors cursor-pointer">
                <svg className="w-5 h-5 text-amber-400 shrink-0" viewBox="0 0 24 24" fill="currentColor">
                  <path d="M3.6 2.1c-.2.2-.3.5-.3.9v18c0 .4.1.7.3.9l.1.1 10.1-10.1v-.2L3.7 2l-.1.1zM17.4 15.3l-3.2-3.2v-.2l3.2-3.2.1.1 3.8 2.2c1.1.6 1.1 1.6 0 2.2l-3.9 2.1zM13.7 11.9L4.4 21.2c.4.1.9 0 1.3-.2l10-5.7-2-2.4v-1zM13.7 12.1l2-2.4-10-5.7c-.4-.2-.9-.3-1.3-.2l9.3 9.3v-1z" />
                </svg>
                <div className="text-left">
                  <div className="text-[9px] uppercase tracking-wider text-slate-400">Download on</div>
                  <div className="text-xs font-bold text-white leading-none">Google Play</div>
                </div>
              </div>
              {/* Apple App Store Badge Mockup */}
              <div className="flex items-center gap-2.5 px-3 py-2 rounded-xl bg-slate-900 border border-slate-800 hover:border-amber-500/40 transition-colors cursor-pointer">
                <svg className="w-5 h-5 text-amber-400 shrink-0" viewBox="0 0 24 24" fill="currentColor">
                  <path d="M18.71 19.5c-.83 1.24-1.71 2.45-3.05 2.47-1.34.03-1.77-.79-3.29-.79-1.53 0-2 .77-3.27.82-1.31.05-2.3-1.32-3.14-2.53C4.25 17 2.94 12.45 4.7 9.39c.87-1.52 2.43-2.48 4.12-2.51 1.28-.02 2.5.87 3.29.87.78 0 2.26-1.07 3.81-.91.65.03 2.47.26 3.64 1.98-.09.06-2.17 1.28-2.15 3.81.03 3.02 2.65 4.03 2.68 4.04-.03.07-.42 1.44-1.38 2.83M15.97 6.42c.62-.75 1.04-1.8 1.01-2.85-.9.04-1.99.6-2.64 1.35-.57.65-1.07 1.7-1 2.73 1 .08 2.01-.48 2.63-1.23z" />
                </svg>
                <div className="text-left">
                  <div className="text-[9px] uppercase tracking-wider text-slate-400">Download on the</div>
                  <div className="text-xs font-bold text-white leading-none">App Store</div>
                </div>
              </div>
            </div>
          </div>

        </div>

        {/* Bottom row */}
        <div className="pt-8 border-t border-slate-850 flex flex-col sm:flex-row items-center justify-between gap-4 text-slate-500">
          <div>
            © 2026 Elevora. All rights reserved. Built for modern executive communication.
          </div>
          <div className="flex items-center gap-6">
            <a href="#privacy" className="hover:text-slate-400 transition-colors">Privacy Policy</a>
            <a href="#terms" className="hover:text-slate-400 transition-colors">Terms of Service</a>
            <a href="#security" className="hover:text-slate-400 transition-colors">Security</a>
          </div>
        </div>

      </div>
    </footer>
  );
};
