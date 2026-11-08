import React from 'react';
import { ElevoraLogo } from './ElevoraLogo';

export const Footer: React.FC = () => {
  return (
    <footer className="bg-[#050B17] border-t border-slate-800 text-slate-400 text-xs">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pt-12 pb-8">
        <div className="grid grid-cols-2 md:grid-cols-5 gap-8 mb-10">
          
          {/* Brand Col */}
          <div className="col-span-2 space-y-3">
            <ElevoraLogo size="md" showSubtitle={true} />
            <p className="text-[11px] text-slate-400 max-w-xs leading-relaxed pt-1">
              Structured managerial communications curriculum designed for ambitious leaders, managers and job-seekers.
            </p>
          </div>

          {/* Quick Links */}
          <div className="space-y-2">
            <h4 className="text-xs font-bold text-white uppercase tracking-wider">Quick Links</h4>
            <ul className="space-y-1.5 text-[11px]">
              <li><a href="#hero" className="hover:text-amber-400 transition-colors">Home</a></li>
              <li><a href="#courses" className="hover:text-amber-400 transition-colors">Courses</a></li>
              <li><a href="#pricing" className="hover:text-amber-400 transition-colors">Pricing</a></li>
              <li><a href="#about" className="hover:text-amber-400 transition-colors">About Us</a></li>
            </ul>
          </div>

          {/* Resources */}
          <div className="space-y-2">
            <h4 className="text-xs font-bold text-white uppercase tracking-wider">Resources</h4>
            <ul className="space-y-1.5 text-[11px]">
              <li><a href="#blog" className="hover:text-amber-400 transition-colors">Blog</a></li>
              <li><a href="#courses" className="hover:text-amber-400 transition-colors">Learning Tips</a></li>
              <li><a href="#pricing" className="hover:text-amber-400 transition-colors">FAQ</a></li>
              <li><a href="mailto:support@elevora.sriddha.com" className="hover:text-amber-400 transition-colors">Contact</a></li>
            </ul>
          </div>

          {/* Get The App Badges */}
          <div className="space-y-2">
            <h4 className="text-xs font-bold text-white uppercase tracking-wider">Get the App</h4>
            <div className="space-y-1.5 pt-1">
              {/* Google Play */}
              <div className="flex items-center gap-2 px-2.5 py-1.5 rounded-lg bg-black border border-slate-700 hover:border-amber-400 transition-colors cursor-pointer w-32">
                <span className="text-base text-amber-400">▶</span>
                <div className="leading-tight text-left">
                  <div className="text-[7px] uppercase tracking-wider text-slate-400">GET IT ON</div>
                  <div className="text-[10px] font-bold text-white">Google Play</div>
                </div>
              </div>
              {/* App Store */}
              <div className="flex items-center gap-2 px-2.5 py-1.5 rounded-lg bg-black border border-slate-700 hover:border-amber-400 transition-colors cursor-pointer w-32">
                <span className="text-base text-white"></span>
                <div className="leading-tight text-left">
                  <div className="text-[7px] uppercase tracking-wider text-slate-400">Download on the</div>
                  <div className="text-[10px] font-bold text-white">App Store</div>
                </div>
              </div>
            </div>
          </div>

        </div>

        {/* Bottom row */}
        <div className="pt-6 border-t border-slate-900 flex flex-col sm:flex-row items-center justify-between gap-3 text-[11px] text-slate-500">
          <div>
            © 2026 Elevora. All rights reserved.
          </div>
          <div className="flex items-center gap-5">
            <a href="#privacy" className="hover:text-slate-400">Privacy Policy</a>
            <span>|</span>
            <a href="#terms" className="hover:text-slate-400">Terms of Use</a>
          </div>
          <div className="flex items-center gap-3 text-slate-400">
            <span className="hover:text-amber-400 cursor-pointer">in</span>
            <span className="hover:text-amber-400 cursor-pointer">▶</span>
            <span className="hover:text-amber-400 cursor-pointer">📷</span>
          </div>
        </div>

      </div>
    </footer>
  );
};
