import React from 'react';
import { Users, CheckCircle2, ArrowRight } from 'lucide-react';

interface BusinessBannerSectionProps {
  onContactSales?: () => void;
}

export const BusinessBannerSection: React.FC<BusinessBannerSectionProps> = ({ onContactSales }) => {
  return (
    <section id="business" className="py-12 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div className="relative rounded-3xl overflow-hidden bg-gradient-to-r from-[#0C1527] via-[#101C33] to-[#0A1120] border border-amber-500/30 p-8 sm:p-12 shadow-2xl">
        
        {/* Glow shape */}
        <div className="absolute right-0 top-0 bottom-0 w-1/3 bg-amber-500/10 blur-3xl pointer-events-none"></div>

        <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-center relative z-10">
          
          {/* Small thumbnail of corporate team */}
          <div className="lg:col-span-4 hidden sm:block">
            <div className="rounded-2xl overflow-hidden border border-slate-700/80 shadow-lg">
              <img 
                src="/assets/business-team.jpg" 
                alt="Corporate Team Training with Elevora" 
                className="w-full h-44 object-cover object-top"
              />
            </div>
          </div>

          {/* Center Info */}
          <div className="lg:col-span-5 space-y-3">
            <div className="inline-flex items-center gap-2 text-amber-400 font-bold text-sm">
              <Users className="w-4 h-4" />
              <span>Elevora for Businesses</span>
            </div>
            <h3 className="text-2xl sm:text-3xl font-extrabold text-white">
              Upskill Your Entire Team's Communication
            </h3>
            <p className="text-slate-300 text-sm leading-relaxed">
              Equip your engineers, product managers, and operations staff to communicate seamlessly with global clients and leadership.
            </p>
          </div>

          {/* Right Checklist & Button */}
          <div className="lg:col-span-3 space-y-4">
            <div className="space-y-2 text-xs font-semibold text-slate-200">
              <div className="flex items-center gap-2">
                <CheckCircle2 className="w-4 h-4 text-emerald-400 shrink-0" />
                <span>Job-ready communication skills</span>
              </div>
              <div className="flex items-center gap-2">
                <CheckCircle2 className="w-4 h-4 text-emerald-400 shrink-0" />
                <span>Flexible and scalable learning</span>
              </div>
              <div className="flex items-center gap-2">
                <CheckCircle2 className="w-4 h-4 text-emerald-400 shrink-0" />
                <span>HR progress reports & analytics</span>
              </div>
            </div>

            <button
              onClick={onContactSales || (() => window.location.href = "mailto:enterprise@elevora.sriddha.com?subject=Elevora%20Enterprise%20Inquiry")}
              className="w-full py-3 px-4 bg-gradient-to-r from-amber-400 to-yellow-400 hover:from-amber-300 hover:to-amber-400 text-slate-950 font-bold text-xs sm:text-sm rounded-xl shadow-lg shadow-amber-500/20 flex items-center justify-center gap-1.5 transition-all"
            >
              <span>Know More for Teams</span>
              <ArrowRight className="w-4 h-4" />
            </button>
          </div>

        </div>
      </div>
    </section>
  );
};
